package com.example.spring.basicboard.controller;

import com.example.spring.basicboard.config.jwt.TokenProvider;
import com.example.spring.basicboard.config.jwt.TokenStatus;
import com.example.spring.basicboard.domain.entity.Member;
import com.example.spring.basicboard.dto.TokenRefreshRequestDto;
import com.example.spring.basicboard.dto.TokenRefreshResponseDto;
import com.example.spring.basicboard.exception.InvalidRefreshTokenException;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/tokens")
public class TokenApiController {

    private final TokenProvider tokenProvider;

    @PostMapping("/refresh")
    public TokenRefreshResponseDto refresh(
            @RequestBody TokenRefreshRequestDto dto
    ) {
        String refreshToken = dto.getRefreshToken();

        TokenStatus status =
                tokenProvider.validateToken(refreshToken);

        if (status != TokenStatus.VALID
                || !tokenProvider.isRefreshToken(refreshToken)) {
            throw new InvalidRefreshTokenException(
                    "Refresh Token이 유효하지 않습니다."
            );
        }

        Member member =
                tokenProvider.getTokenDetails(refreshToken);

        String newAccessToken =
                tokenProvider.generateAccessToken(member);

        return new TokenRefreshResponseDto(newAccessToken);
    }
}
