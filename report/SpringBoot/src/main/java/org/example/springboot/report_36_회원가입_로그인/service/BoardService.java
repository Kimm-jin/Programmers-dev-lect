package org.example.springboot.report_36_회원가입_로그인.service;

import lombok.RequiredArgsConstructor;
import org.example.springboot.report_36_회원가입_로그인.domain.entity.Board;
import org.example.springboot.report_36_회원가입_로그인.domain.repository.BoardRepository;
import org.example.springboot.report_36_회원가입_로그인.dto.BoardDeleteRequestDto;
import org.example.springboot.report_36_회원가입_로그인.dto.BoardDetailResponseDto;
import org.example.springboot.report_36_회원가입_로그인.dto.BoardUpdateRequestDto;
import org.example.springboot.report_36_회원가입_로그인.exception.BoardNotFoundException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class BoardService {

    private final BoardRepository boardRepository;
    @Value("${file.upload-dir}")
    private String uploadDir;

    @Transactional(readOnly = true)
    public List<Board> getBoardList(int page, int size){
        Pageable pageable = PageRequest.of(page-1, size, Sort.by("id").descending());
        return boardRepository.findAll(pageable).getContent();
    }

    @Transactional(readOnly = true)
    public int getTotalBoards(){
        return (int)boardRepository.count();
    }

    public Board getBoardDetail(long id){
        return boardRepository.findById(id)
                .orElseThrow(() -> new BoardNotFoundException("[BOARD] 게시글을 찾을 수 없습니다."));
    }

    @Transactional
    public void saveArticle(String userId, String title, String content, MultipartFile file) {
        String filePath = storeFile(file);   // 첨부파일 없으면 null 반환

        Board board = Board.builder()
                .userId(userId)
                .title(title)
                .content(content)
                .filePath(filePath)
                .created(LocalDateTime.now())
                .build();

        boardRepository.save(board);
    }

    public String storeFile(MultipartFile file){
        if(file==null|| file.isEmpty())return null;
        try{
            File dir = new File(uploadDir).getAbsoluteFile();
            if(!dir.exists())dir.mkdirs();

            String storedFileName = UUID.randomUUID() + "_" + file.getOriginalFilename();
            File dest = new File(dir, storedFileName);

            file.transferTo(dest);

            return dest.getPath();
        } catch (IOException e) {
            throw new IllegalStateException("파일 저장에 실패 했습니다.", e);
        }
    }

    public Resource downloadFile(String fileName){
        try{
            File file = new File(new File(uploadDir).getAbsoluteFile(), fileName);
            UrlResource resource = new UrlResource(file.toURI());
            if(!resource.exists() || !resource.isReadable()){
                throw new BoardNotFoundException("파일을 찾을 수 없습니다. fileName=" + fileName);
            }
            return resource;
        } catch (MalformedURLException e) {
            throw new IllegalStateException("파일 경로가 잘못되었습니다.", e);
        }
    }

    @Transactional
    public void deleteArticle(Long id, BoardDeleteRequestDto request){
        if(!boardRepository.existsById(id)){
            throw new BoardNotFoundException("게시글을 찾을 수 없습니다. id="+id);
        }
        boardRepository.deleteById(id);
        deleteFile(request.getFilePath());
    }

    private void deleteFile(String filePath) {
        if(filePath == null || filePath.isBlank())return;
        File file = new File(filePath);
        if(file.exists())file.delete();
    }

    @Transactional
    public void updateArticle(Long id, BoardUpdateRequestDto request){

    }
}
