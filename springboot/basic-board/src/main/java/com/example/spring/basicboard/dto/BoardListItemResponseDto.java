package com.example.spring.basicboard.dto;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class BoardListItemResponseDto {
    private Long id;
    private String userId;
    private String title;
    private LocalDateTime created;
}
