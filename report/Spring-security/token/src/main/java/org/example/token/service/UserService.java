package org.example.token.service;

import lombok.RequiredArgsConstructor;
import org.example.token.config.security.CustomUserDetails;
import org.example.token.domain.entity.User;
import org.example.token.domain.repository.UserRepository;
import org.example.token.dto.SignInResponseDto;
import org.example.token.dto.SignUpRequestDto;
import org.example.token.exception.DuplicateUserIdException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;

    public SignInResponseDto signIn(String username, String password){
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(username, password)
        );

        CustomUserDetails principal = (CustomUserDetails) authentication.getPrincipal();

        User user = principal.getUser();

        TokenService.TokenPair tokens = tokenService.issueTokens(user);

        return SignInResponseDto.builder()
                .isLoggedIn(true)
                .message("로그인 성공")
                .url("/")
                .accessToken(tokens.accessToken())
                .refreshToken(tokens.refreshToken())
                .userId(user.getUserId())
                .userName(user.getName())
                .build();

    }

    @Transactional
    public void signUp(SignUpRequestDto requestDto) {
        if (userRepository.existsByUserId(requestDto.getUserId())) {
            throw new DuplicateUserIdException(
                    "[회원가입] 이미 사용 중인 아이디입니다."
            );
        }

        User user = requestDto.toUser(
                passwordEncoder.encode(requestDto.getPassword())
        );

        userRepository.save(user);
    }
}
