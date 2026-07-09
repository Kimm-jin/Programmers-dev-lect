package org.example.springboot.report_36_회원가입_로그인.domain.repository;

import org.example.springboot.report_36_회원가입_로그인.domain.entity.Member;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.boot.jdbc.test.autoconfigure.AutoConfigureTestDatabase;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class MemberRepositoryTest {
    @Autowired
    private MemberRepository memberRepository;

    @BeforeEach
    void setUp(){
        Member member = Member.builder()
                .userId("test1")
                .password("1234")
                .userName("홍길순")
                .build();
    }

    @Test
    void existsByUserId_존재하면_true(){
        assertThat(memberRepository.existsByUserId("test1")).isTrue();
        assertThat(memberRepository.existsByUserId("nobody")).isFalse();
    }

    @Test
    void findByUserId_있으면_회원(){
        assertThat(memberRepository.findByUserId("test1")).isPresent();
        assertThat(memberRepository.findByUserId("nobody")).isEmpty();
    }
}