package com.example.spring.basicboard.config;

import com.example.spring.basicboard.config.filter.LoginFilter;
import com.example.spring.basicboard.config.filter.TokenAuthenticationFilter;
import com.example.spring.basicboard.config.jwt.TokenProvider;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.MediaType;
import tools.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.nio.charset.StandardCharsets;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig {

    private final TokenAuthenticationFilter tokenAuthenticationFilter;
    private final TokenProvider tokenProvider;
    private final ObjectMapper objectMapper;

    @Bean
    public SecurityFilterChain securityFilterChain(
            HttpSecurity http,
            AuthenticationManager authenticationManager
    ) throws Exception {

        LoginFilter loginFilter = new LoginFilter(
                authenticationManager,
                tokenProvider,
                objectMapper
        );

        loginFilter.setFilterProcessesUrl("/api/members/login");

        return http
                .csrf(csrf -> csrf.disable())

                .formLogin(formLogin -> formLogin.disable())

                .httpBasic(httpBasic -> httpBasic.disable())

                .sessionManagement(session ->
                        session.sessionCreationPolicy(
                                SessionCreationPolicy.STATELESS
                        )
                )

                .exceptionHandling(exception -> exception

                        .authenticationEntryPoint((request, response, authException) -> {
                            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                            response.setCharacterEncoding(StandardCharsets.UTF_8.name());
                            response.setContentType(MediaType.APPLICATION_JSON_VALUE);

                            response.getWriter().write(
                                    """
                                    {
                                      "status": 401,
                                      "message": "인증이 필요합니다."
                                    }
                                    """
                            );
                        })

                        .accessDeniedHandler((request, response, accessDeniedException) -> {
                            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
                            response.setCharacterEncoding(StandardCharsets.UTF_8.name());
                            response.setContentType(MediaType.APPLICATION_JSON_VALUE);

                            response.getWriter().write(
                                    """
                                    {
                                      "status": 403,
                                      "message": "접근 권한이 없습니다."
                                    }
                                    """
                            );
                        })
                )

                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(
                                "/",
                                "/write",
                                "/detail",
                                "/update/**",
                                "/stats",

                                "/members/**",
                                "/api/members/join",
                                "/api/members/login",
                                "/api/tokens/refresh",

                                "/api/boards/file/download/**",

                                "/css/**",
                                "/js/**",
                                "/images/**",
                                "/favicon.ico"
                        ).permitAll()

                        .anyRequest().authenticated()
                )

                .addFilterAt(
                        loginFilter,
                        UsernamePasswordAuthenticationFilter.class
                )

                .addFilterBefore(
                        tokenAuthenticationFilter,
                        LoginFilter.class
                )

                .build();
    }

    @Bean
    public AuthenticationManager authenticationManager(
            AuthenticationConfiguration configuration
    ) throws Exception {
        return configuration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
