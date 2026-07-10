package com.example.spring.basicboard.mapper;

import com.example.spring.basicboard.domain.entity.Board;
import com.example.spring.basicboard.domain.entity.Member;
import com.example.spring.basicboard.dto.BoardListItemResponseDto;
import com.example.spring.basicboard.dto.MemberJoinRequestDto;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Component
public class BoardMapper {

    // Entity -> DTO
    public BoardListItemResponseDto toDto(Board board){
        return BoardListItemResponseDto.builder()
                .id(board.getId())
                .title(board.getTitle())
                .userId(board.getUserId())
                .created(board.getCreated())
                .build();
    }

    // List<Entity> -> List<DTO>
    public List<BoardListItemResponseDto> toDtoList(List<Board> boards){
        List<BoardListItemResponseDto> res = new ArrayList<>();

        for(Board board : boards){
            res.add(toDto(board));
        }
        return res;
    }
}
