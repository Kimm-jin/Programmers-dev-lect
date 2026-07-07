package org.example.springboot.report_36_회원가입.service;


import lombok.RequiredArgsConstructor;
import org.example.springboot.report_36_회원가입.domain.entity.Member;
import org.example.springboot.report_36_회원가입.domain.repository.MemberRepository;
import org.example.springboot.report_36_회원가입.dto.MemberJoinRequestDto;
import org.example.springboot.report_36_회원가입.exception.DuplicateUserIdException;
import org.example.springboot.report_36_회원가입.mapper.MemberMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;
    private final MemberMapper memberMapper;

    @Transactional
    public void join(MemberJoinRequestDto request) {
        if (memberRepository.existsByUserId(request.getUserId())) {
            throw new DuplicateUserIdException("이미 존재하는 아이디입니다.");
        }
        Member member = memberMapper.toEntity(request);
        memberRepository.save(member);
    }


}
