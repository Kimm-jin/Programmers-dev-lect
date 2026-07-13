package com.example.spring.basicboard.domain.repository;

import com.example.spring.basicboard.dto.BoardListItemResponseDto;
import com.example.spring.basicboard.dto.BoardSearchRequestDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BoardRepositoryCustom {

    Page<BoardListItemResponseDto> searchBoards(BoardSearchRequestDto dto, Pageable pageable);

}
