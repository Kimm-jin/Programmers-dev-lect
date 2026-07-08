package com.example.spring.basicboard.controller;

import com.example.spring.basicboard.domain.entity.Board;
import com.example.spring.basicboard.dto.BoardDetailResponseDto;
import com.example.spring.basicboard.dto.BoardListResponseDto;
import com.example.spring.basicboard.dto.BoardWriteRequestDto;
import com.example.spring.basicboard.service.BoardService;
import com.example.spring.basicboard.service.FileService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/boards")
public class BoardApiController {
    private final BoardService boardService;
    private final FileService fileService;

    @GetMapping
    public BoardListResponseDto getBoardList(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10")int size
    ){
        // 게시글 목록
        List<Board> boards = boardService.getBoardList(page,size);

        // 전체 게시글 수 가져오기
        int totalBoards = boardService.getTotalBoards();

        // 전체 페이지 수 계산
        int totalPages = (int) Math.ceil((double) totalBoards / size);

        // 마지막 페이지 여부
        boolean last = page >= totalPages;

        return BoardListResponseDto.builder()
                .boards(boards)
                .last(last)
                .totalPages(totalPages)
                .build();
    }

    @PostMapping
    public void saveBoard(@ModelAttribute BoardWriteRequestDto dto){
        boardService.saveBoard(dto.getUserId(), dto.getTitle(), dto.getContent(), dto.getFile());
    }

    @GetMapping("/{id}")
    public BoardDetailResponseDto getBoardDetail(@PathVariable long id){
        Board boardDetail = boardService.getBoardDetail(id);
        return BoardDetailResponseDto.builder()
                .title(boardDetail.getTitle())
                .content(boardDetail.getContent())
                .filePath(boardDetail.getFilePath())
                .created(boardDetail.getCreated())
                .userId(boardDetail.getUserId())
                .build();
    }

    // ResponseEntity는 HTTP응답의 3가지를 직접 제어하게 해주는 상자다
    // [상태코드] + [헤더] + [본문(body)]
    // 그냥 Resource만 리턴하면 파일 내용은 내려가지만,
    // Content-Disposition: attachment 헤더를 붙일 방법이 없다.
    // -> 그러면 다운로드가 아니라 브라우저가 파일을 그냥 열어버리고, 저장 파일명도 못 정한다.
    @GetMapping("/file/download/{fileName}")
    public ResponseEntity<Resource> downloadFile(@PathVariable String fileName){
        Resource resource = fileService.downloadFile(fileName);

        // * 한글 파일명 인코딩
        // HTTP 헤더 값에는 원칙적으로 ASCII만 안전하게 담을 수 있다.
        // -> "이력서.pdf"같은 한글/공백을 그대로 넣으면 깨지거나 잘린다.
        // 그래서 파일명을 URL 인코딩해서 넣는다.
        String encodedFileName = URLEncoder.encode(resource.getFilename(), StandardCharsets.UTF_8)
                .replaceAll("\\+", "%20");
        //   - URLEncoder 는 공백을 '+' 로 바꾸는데, 파일명에선 '+' 가 그대로 보이면 곤란하므로 %20 으로 치환한다

        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename*=UTF-8''"+encodedFileName)
                .body(resource);
    }
}