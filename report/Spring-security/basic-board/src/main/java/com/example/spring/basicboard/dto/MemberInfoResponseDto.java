package com.example.spring.basicboard.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class MemberInfoResponseDto {
    private String userId;
    private String userName;
    private String role;
}
