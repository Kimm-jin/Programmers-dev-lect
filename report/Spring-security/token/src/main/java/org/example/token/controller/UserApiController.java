package org.example.token.controller;


import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.example.token.config.jwt.JwtProperties;
import org.example.token.config.security.CustomUserDetails;
import org.example.token.domain.entity.User;
import org.example.token.dto.*;
import org.example.token.service.UserService;
import org.example.token.util.CookieUtil;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserApiController {
    private final UserService userService;
    private final JwtProperties jwtProperties;

    @PostMapping("/login")
    public SignInResponseDto signIn(
            @RequestBody SignInRequestDto requestDto,
            HttpServletResponse response
    ){
        SignInResponseDto result = userService.signIn(
                requestDto.getUserId(),
                requestDto.getPassword()
        );

        CookieUtil.addCookie(
                response,
                CookieUtil.REFRESH_TOKEN_COOKIE,
                result.getRefreshToken(),
                (int) jwtProperties.getRefreshTokenValidity().toSeconds()
        );

        result.setRefreshToken(null);

        return result;
    }

    @PostMapping("/join")
    public SignUpResponseDto signUp(
            @RequestBody SignUpRequestDto requestDto
    ) {
        userService.signUp(requestDto);

        return SignUpResponseDto.builder()
                .url("/users/login")
                .build();
    }

    @GetMapping("/info")
    public UserInfoResponseDto getUserInfo(
            @AuthenticationPrincipal CustomUserDetails userDetails
    ) {
        User user = userDetails.getUser();

        return UserInfoResponseDto.builder()
                .id(user.getId())
                .userId(user.getUserId())
                .userName(user.getName())
                .role(user.getRole())
                .build();
    }

    @PostMapping("/logout")
    public LogoutResponseDto logout(
            HttpServletRequest request,
            HttpServletResponse response
    ) {
        CookieUtil.deleteCookie(
                request,
                response,
                CookieUtil.REFRESH_TOKEN_COOKIE
        );

        return LogoutResponseDto.builder()
                .message("로그아웃 되었습니다.")
                .url("/users/login")
                .build();
    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping("/user")
    public AuthorityResponseDto authority() {
        return AuthorityResponseDto.builder()
                .message("일반 사용자만 볼 수 있는 권한입니다.")
                .build();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/admin")
    public AuthorityResponseDto authorityAdmin() {
        return AuthorityResponseDto.builder()
                .message("관리자만 볼 수 있는 권한입니다.")
                .build();
    }
}
