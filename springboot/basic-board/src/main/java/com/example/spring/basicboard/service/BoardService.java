package com.example.spring.basicboard.service;


import com.example.spring.basicboard.domain.entity.Board;
import com.example.spring.basicboard.domain.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class BoardService {

    private final BoardRepository boardRepository;
    private final FileService fileService;

    // pagenation
    public List<Board> getBoardList(int page, int size){
        Pageable pageable = PageRequest.of(page - 1, size, Sort.by("id").descending());

        // * findAll(pageable).getContent()의 getContent()란?
        // findAll(pageable)의 반환 타입은 Page<Board>다
        // Page가 제공하는 것들
        // - getContent() -> List<Board>
        // - getTotalElements() -> Long : 전체 게시글 수
        // - getTotalPages() -> int : 전체 페이지 수
        // - isLast() -> bollean : 마지막 페이지 여부
        // 주의 : getContent()의 'content'는 Board 엔티티의 content가 아니다. / Page에 대한 content
        return boardRepository.findAll(pageable).getContent();
    }

    public int getTotalBoards(){
        return (int) boardRepository.count();
    }


    @Transactional
    public void saveBoard(String userId, String title, String content, MultipartFile file){
        String filePath = fileService.storeFile(file);

        Board bulid = Board.builder()
                .userId(userId)
                .title(title)
                .content(content)
                .filePath(filePath)
                .build();
        boardRepository.save(bulid);
    }

}
