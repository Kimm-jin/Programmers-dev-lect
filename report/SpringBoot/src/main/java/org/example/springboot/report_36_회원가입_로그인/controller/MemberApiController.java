package org.example.springboot.report_36_회원가입.controller;

import jakarta.servlet.http.HttpSession;
import org.example.springboot.report_36_회원가입.constant.SessionConst;
import org.example.springboot.report_36_회원가입.dto.LoginRequestDto;
import org.example.springboot.report_36_회원가입.dto.LoginResponseDto;
import org.example.springboot.report_36_회원가입.dto.MemberJoinRequestDto;
import org.example.springboot.report_36_회원가입.dto.MemberJoinResponseDto;
import org.example.springboot.report_36_회원가입.service.MemberService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/members")
public class MemberApiController {

    private final MemberService memberService;

    public MemberApiController(MemberService memberService) {
        this.memberService = memberService;
    }

    @PostMapping("/join")
    public MemberJoinResponseDto join(@RequestBody MemberJoinRequestDto requestDto){
        memberService.join(requestDto);
        return new MemberJoinResponseDto("/members/login");
    }

    @PostMapping("/login")
    public LoginResponseDto login(@RequestBody LoginRequestDto dto, HttpSession session){
        return memberService.login(dto)
                .map(member -> {
                    session.setAttribute(SessionConst.USER_ID, member.getUserId());
                    session.setAttribute(SessionConst.USER_NAME, member.getUserName());
                    return LoginResponseDto.success();
                })
                .orElseGet(LoginResponseDto::fail);
    }

}
