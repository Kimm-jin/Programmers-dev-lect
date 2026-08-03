package com.example.spring.basicboard.service;

import com.example.spring.basicboard.config.security.CustomUserDetails;
import com.example.spring.basicboard.domain.entity.Member;
import com.example.spring.basicboard.domain.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final MemberRepository memberRepository;

    @Override
    public CustomUserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {

        Member member = memberRepository.findByUserId(userId)
                .orElseThrow(() ->
                        new UsernameNotFoundException(userId + " not found"));

        return new CustomUserDetails(member);
    }
}
