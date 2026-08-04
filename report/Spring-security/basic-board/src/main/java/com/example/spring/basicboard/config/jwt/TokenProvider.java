package com.example.spring.basicboard.config.jwt;

import com.example.spring.basicboard.config.security.CustomUserDetails;
import com.example.spring.basicboard.constant.MemberRole;
import com.example.spring.basicboard.domain.entity.Member;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.time.Duration;
import java.util.Base64;
import java.util.Date;

@Slf4j
@Service
@RequiredArgsConstructor
public class TokenProvider {

    private static final String CLAIM_ID = "id";
    private static final String CLAIM_NAME = "name";
    private static final String CLAIM_ROLE = "role";
    private static final String CLAIM_TOKEN_TYPE = "tokenType";

    private final JwtProperties jwtProperties;

    private SecretKey secretKey;
    private JwtParser jwtParser;

    @PostConstruct
    private void init() {
        byte[] decodedKey = Base64.getDecoder()
                .decode(jwtProperties.getSecretKey());

        this.secretKey = Keys.hmacShaKeyFor(decodedKey);

        this.jwtParser = Jwts.parser()
                .verifyWith(secretKey)
                .build();
    }

    public String generateAccessToken(Member member) {
        return generateToken(
                member,
                jwtProperties.getAccessTokenValidity(),
                TokenType.ACCESS
        );
    }

    public String generateRefreshToken(Member member) {
        return generateToken(
                member,
                jwtProperties.getRefreshTokenValidity(),
                TokenType.REFRESH
        );
    }

    private String generateToken(
            Member member,
            Duration validity,
            TokenType tokenType
    ) {
        Date now = new Date();
        Date expiration = new Date(
                now.getTime() + validity.toMillis()
        );

        return makeToken(member, now, expiration, tokenType);
    }

    private String makeToken(
            Member member,
            Date issuedAt,
            Date expiration,
            TokenType tokenType
    ) {
        return Jwts.builder()
                .header()
                .type("JWT")
                .and()
                .issuer(jwtProperties.getIssuer())
                .issuedAt(issuedAt)
                .expiration(expiration)
                .subject(member.getUserId())
                .claim(CLAIM_ID, member.getId())
                .claim(CLAIM_NAME, member.getUserName())
                .claim(CLAIM_ROLE, member.getRole().name())
                .claim(CLAIM_TOKEN_TYPE, tokenType.name())
                .signWith(secretKey, Jwts.SIG.HS512)
                .compact();
    }

    public TokenStatus validateToken(String token) {
        try {
            jwtParser.parseSignedClaims(token);

            log.debug("유효한 토큰입니다.");
            return TokenStatus.VALID;

        } catch (ExpiredJwtException e) {
            log.warn("만료된 토큰입니다.");
            return TokenStatus.EXPIRED;

        } catch (Exception e) {
            log.warn("유효하지 않은 토큰입니다.", e);
            return TokenStatus.INVALID;
        }
    }

    public boolean isRefreshToken(String token) {
        return TokenType.REFRESH.name().equals(
                getClaims(token).get(CLAIM_TOKEN_TYPE, String.class)
        );
    }

    public boolean isAccessToken(String token) {
        return TokenType.ACCESS.name().equals(
                getClaims(token).get(CLAIM_TOKEN_TYPE, String.class)
        );
    }

    public Member getTokenDetails(String token) {
        Claims claims = getClaims(token);

        return Member.builder()
                .id(claims.get(CLAIM_ID, Long.class))
                .userId(claims.getSubject())
                .userName(claims.get(CLAIM_NAME, String.class))
                .role(
                        MemberRole.valueOf(
                                claims.get(CLAIM_ROLE, String.class)
                        )
                )
                .build();
    }

    private Claims getClaims(String token) {
        return jwtParser
                .parseSignedClaims(token)
                .getPayload();
    }

    public Authentication getAuthentication(
            Member member,
            String token
    ) {
        CustomUserDetails principal =
                new CustomUserDetails(member);

        return new UsernamePasswordAuthenticationToken(
                principal,
                token,
                principal.getAuthorities()
        );
    }
}
