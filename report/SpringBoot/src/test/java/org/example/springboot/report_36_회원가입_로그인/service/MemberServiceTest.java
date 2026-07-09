package org.example.springboot.report_36_회원가입_로그인.service;

import org.example.springboot.report_36_회원가입_로그인.domain.entity.Member;
import org.example.springboot.report_36_회원가입_로그인.domain.repository.MemberRepository;
import org.example.springboot.report_36_회원가입_로그인.dto.LoginRequestDto;
import org.example.springboot.report_36_회원가입_로그인.dto.MemberJoinRequestDto;
import org.example.springboot.report_36_회원가입_로그인.exception.DuplicateUserIdException;
import org.example.springboot.report_36_회원가입_로그인.mapper.MemberMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;



@ExtendWith(MockitoExtension.class)
class MemberServiceTest {

    @Mock
    private MemberRepository memberRepository;
    @Mock
    private MemberMapper memberMapper;
    @InjectMocks
    private MemberService memberService;

    @Test
    @DisplayName("로그인 - 아이디가 있고 비밀번호가 일치하면 회원을 담은 Optional을 반환한다.")
    void login_성공(){
        Member member = Member.builder()
                .userId("test1")
                .password("1234")
                .userName("홍길순")
                .build();

        BDDMockito.given( memberRepository.findByUserId("test1") ).willReturn( Optional.of(member) );
        LoginRequestDto requestDto = new LoginRequestDto();
        requestDto.setUsername("test1");
        requestDto.setPassword("1234");

        Optional<Member> result = memberService.login(requestDto);
        assertThat(result).isPresent();
        assertThat(result.get().getUserName()).isEqualTo("홍길순");
    }


    @Test
    @DisplayName("로그인 - 비밀번호가 틀리면 빈 Optional을 반환한다.")
    void login_비밀번호_틀림_빈_Optional(){

        Member member = Member.builder()
                .userId("test1")
                .password("1234")
                .userName("홍길순")
                .build();
        given( memberRepository.findByUserId("test1") ).willReturn( Optional.of(member) );

        LoginRequestDto requestDto = new LoginRequestDto();
        requestDto.setUsername("test1");
        requestDto.setPassword("9999");

        // when
        Optional<Member> result = memberService.login(requestDto);

        // then
        assertThat(result).isEmpty();
    }

    @Test
    @DisplayName("로그인 - 아이디가 없으면 빈 Optional을 반환한다.")
    void login_아이디_없음_빈_Optional(){
        given(memberRepository.findByUserId("nobody")).willReturn(Optional.empty());

        LoginRequestDto requestDto = new LoginRequestDto();
        requestDto.setUsername("nobody");
        requestDto.setPassword("9999");

        Optional<Member> result = memberService.login(requestDto);
        assertThat(result).isEmpty();
    }

    @Test
    @DisplayName("회원가입 - 아이디가 중복이 아니면 회원을 지정한다.")
    void join_중복_아니면_저장(){
        MemberJoinRequestDto requestDto = new MemberJoinRequestDto();
        requestDto.setUserId("test1");
        requestDto.setPassword("1234");
        requestDto.setUserName("홍길순");

        Member member = Member.builder()
                .userId("test1")
                .password("1234")
                .userName("홍길순")
                .build();

        given(memberRepository.existsByUserId("test1")).willReturn(false);
        given(memberMapper.toEntity(requestDto)).willReturn(member);

        memberService.join(requestDto);
        verify(memberRepository).save(member);
    }

    @Test
    @DisplayName("회원가입 - 아이디가 이미 있으면 DuplicateUserIdException을 던지고 저장하지 않는다.")
    void join_중복이면_예외(){
        MemberJoinRequestDto requestDto = new MemberJoinRequestDto();
        requestDto.setUserId("test1");
        requestDto.setPassword("1234");
        requestDto.setUserName("홍길순");

        given(memberRepository.existsByUserId("test1")).willReturn(true);

        assertThatThrownBy(() -> memberService.join(requestDto))
                .isInstanceOf(DuplicateUserIdException.class)
                .hasMessageContaining("[회원가입] 이미 존재하는 아이디입니다.");
        verify(memberRepository, never()).save( any() );
    }

}