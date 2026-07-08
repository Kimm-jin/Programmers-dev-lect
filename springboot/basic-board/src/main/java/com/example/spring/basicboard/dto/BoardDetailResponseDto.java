package com.example.spring.basicboard.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Builder
public class BoardDetailResponseDto {
    private String title;
    private String content;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm") // 2026-06-19T12:15
    private LocalDateTime created;
    private String userId;
    private String filePath;
}
