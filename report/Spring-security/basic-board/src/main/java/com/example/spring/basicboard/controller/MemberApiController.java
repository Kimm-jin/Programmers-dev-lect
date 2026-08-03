package com.example.spring.basicboard.controller;

import com.example.spring.basicboard.config.security.CustomUserDetails;
import com.example.spring.basicboard.constant.SessionConst;
import com.example.spring.basicboard.dto.*;
import com.example.spring.basicboard.service.MemberService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@Tag( name = "회원 API", description = "회원가입, 로그인, 로그아웃 (세션 기반, spring security 미사용)")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/members")
public class MemberApiController {

    private final MemberService memberService;

    @Operation(summary = "회원가입", description = "아이디/비밀번호/이름으로 새 회원을 등록한다. 성공 시 로그인 페이지 경로를 돌려준다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "가입 성공"),
            @ApiResponse(responseCode = "409", description = "이미 존재하는 아이디",
                    content = @Content(schema = @Schema(implementation = ErrorResponseDto.class)))
    })
    @PostMapping("/join")
    public MemberJoinReponseDto join(@RequestBody MemberJoinRequestDto dto) {
        memberService.join( dto );
        return new MemberJoinReponseDto("/members/login");
    }

    @GetMapping("/info")
    public MemberInfoResponseDto getMemberInfo(
            @AuthenticationPrincipal CustomUserDetails userDetails
    ) {
        return MemberInfoResponseDto.builder()
                .userId(userDetails.getMember().getUserId())
                .userName(userDetails.getMember().getUserName())
                .role(userDetails.getMember().getRole().name())
                .build();
    }
}
