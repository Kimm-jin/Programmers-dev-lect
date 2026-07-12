package org.example.springboot.report_36_회원가입_로그인.controller;

import lombok.RequiredArgsConstructor;
import org.example.springboot.report_36_회원가입_로그인.domain.entity.Board;
import org.example.springboot.report_36_회원가입_로그인.dto.*;
import org.example.springboot.report_36_회원가입_로그인.service.BoardService;
import org.example.springboot.report_36_회원가입_로그인.service.FileService;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
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
            @RequestParam(defaultValue = "10") int size
    ){
        List<Board> boards = boardService.getBoardList(page, size);
        int totalBoards = boardService.getTotalBoards();
        int totalPages = (int)Math.ceil((double) totalBoards/size);
        boolean last = page>=totalPages;

        return BoardListResponseDto.builder()
                .boards(boards)
                .last(last)
                .totalPages(totalPages)
                .build();
    }

    @GetMapping("/{id}")
    public BoardDetailResponseDto getBoardDetail(@PathVariable long id){
        Board board = boardService.getBoardDetail(id);
        return BoardDetailResponseDto.builder()
                .title(board.getTitle())
                .content(board.getContent())
                .userId(board.getUserId())
                .created(board.getCreated())
                .filePath(board.getFilePath())
                .build();
    }

    @GetMapping("/file/download/{fileName}")
    public ResponseEntity<Resource> downloadFile(@PathVariable String fileName) {
        Resource resource = fileService.downloadFile(fileName);

        String encodedFileName = URLEncoder.encode(resource.getFilename(), StandardCharsets.UTF_8)
                .replaceAll("\\+", "%20");

        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename*=UTF-8''" + encodedFileName)
                .body(resource);
    }

    @PostMapping
    public void saveArticle(@ModelAttribute BoardWriteRequestDto dto){
        boardService.saveArticle(dto.getUserId(), dto.getTitle(), dto.getContent(), dto.getFile());
    }

    @DeleteMapping("/{id}")
    public void deleteArticle(@PathVariable long id, @RequestBody BoardDeleteRequestDto dto){
        boardService.deleteArticle(id, dto);
    }

    @PutMapping("/{id}")
    public void updateBoard(@PathVariable long id, @ModelAttribute BoardUpdateRequestDto dto){
        boardService.updateArticle(id, dto);
    }
}
