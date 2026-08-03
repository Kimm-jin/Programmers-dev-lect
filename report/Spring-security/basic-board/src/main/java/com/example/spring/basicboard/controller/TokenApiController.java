package com.example.spring.basicboard.controller;

import com.example.spring.basicboard.config.jwt.JwtProperties;
import com.example.spring.basicboard.config.jwt.TokenProvider;
import com.example.spring.basicboard.config.jwt.TokenStatus;
import com.example.spring.basicboard.domain.entity.Member;
import com.example.spring.basicboard.dto.TokenRefreshRequestDto;
import com.example.spring.basicboard.dto.TokenRefreshResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/tokens")
public class TokenApiController {

    private final TokenProvider tokenProvider;
    private final JwtProperties jwtProperties;

    @PostMapping("/refresh")
    public TokenRefreshResponseDto refresh(
            @RequestBody TokenRefreshRequestDto dto
    ) {
        String refreshToken = dto.getRefreshToken();

        TokenStatus status =
                tokenProvider.validateToken(refreshToken);

        if (status != TokenStatus.VALID) {
            throw new ResponseStatusException(
                    HttpStatus.UNAUTHORIZED,
                    "Refresh Token이 유효하지 않습니다."
            );
        }

        Member member =
                tokenProvider.getTokenDetails(refreshToken);

        String newAccessToken =
                tokenProvider.generateToken(
                        member,
                        jwtProperties.getAccessTokenValidity()
                );

        return new TokenRefreshResponseDto(newAccessToken);
    }
}
