package org.example.springboot.report_36_회원가입_로그인.mapper;

import org.example.springboot.report_36_회원가입_로그인.domain.entity.Member;
import org.example.springboot.report_36_회원가입_로그인.dto.MemberJoinRequestDto;
import org.springframework.stereotype.Component;

@Component
public class MemberMapper {
    public Member toEntity(MemberJoinRequestDto request){
        return Member.builder()
                .userId(request.getUserId())
                .password(request.getPassword())   // 학습용: 평문 그대로
                .userName(request.getUserName())
                .build();
    }
}
