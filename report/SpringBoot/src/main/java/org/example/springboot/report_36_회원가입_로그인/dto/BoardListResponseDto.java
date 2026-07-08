package org.example.springboot.report_36_회원가입_로그인.dto;

import lombok.Builder;
import lombok.Getter;
import org.example.springboot.report_36_회원가입_로그인.domain.entity.Board;

import java.util.List;

@Getter
@Builder
public class BoardListResponseDto {
    private List<Board> boards;
    private boolean last;
    private int totalPages;
}
