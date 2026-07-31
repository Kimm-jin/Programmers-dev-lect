package org.example.oauth2.config;

// * OAuth2 (Open Authorization 2.0)
// OAuth2는 "비밀번호를 넘겨주지 않고 권한을 위임"하기 위한 표준 프레임워크
// OAuth2 사용 방법:
// 사용자가 "카카오에게 직접" 허락받고
// 우리 서비스는 그 허락의 증표(access token)만 받는다.
// -> 비밀번호는 원래 주인(카카오)만 알고, 위임 범위와 회수가 가능해진다.

// 역할
// 1) Resource Owner :  자원의 주인 = 사용자 (카카오 계정의 주인)
// 2) Client : 자원을 쓰고 싶은 제3자 앱 = 우리 서비스
// 3) Authorization Server : 허락(인가)을 발급하는 서버 = kauth.kakao.com
// 4) Resource Server : 실제 자원(프로필 등)을 보관한 서버 = kapi.kakao.com

public class SecurityConfig {
}
