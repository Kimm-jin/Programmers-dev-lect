# Basic Board

> 기존 세션 인증을 **Spring Security + JWT**로 전환한 게시판

## JWT Flow 한눈에 보기

```mermaid
flowchart LR
    U["사용자"] -->|"① ID / PW 로그인"| A["Spring Security<br/>AuthenticationManager"]
    A -->|"② 회원 조회 · 비밀번호 검증"| DB[("Member DB")]
    A -->|"③ 인증 성공"| T["Token Provider"]

    T -->|"④ Access Token"| C["Client"]
    T -->|"④ Refresh Token<br/>HttpOnly Cookie"| C

    C -->|"⑤ Bearer Access Token"| F["JWT Filter"]
    F -->|"⑥ 서명 · 만료 검증"| T
    F -->|"⑦ 인증 객체 저장"| S["SecurityContext"]
    S -->|"⑧ Role / 작성자 검사"| API["Board API"]
    API -->|"⑨ Response"| C

    C -.->|"Access Token 만료"| R["Refresh API"]
    R -.->|"Refresh Token 검증"| T
    T -.->|"새 Token Pair 발급"| C
```

```text
로그인 → 토큰 발급 → Bearer Token 전송 → JWT 검증
      → SecurityContext 생성 → 권한 검사 → API 실행
      → Access Token 만료 시 Refresh Token으로 재발급
```

## 1. 로그인과 Token 발급

```mermaid
sequenceDiagram
    autonumber
    actor User
    participant Client
    participant API as Login API
    participant Security as AuthenticationManager
    participant UDS as UserDetailsService
    participant DB as Member DB
    participant JWT as TokenProvider

    User->>Client: ID / Password 입력
    Client->>API: POST /api/members/login
    API->>Security: UsernamePasswordAuthenticationToken
    Security->>UDS: loadUserByUsername(userId)
    UDS->>DB: 회원 조회
    DB-->>UDS: Member + Role + Password Hash
    Security->>Security: BCrypt 비밀번호 비교

    alt 인증 성공
        Security-->>API: Authentication
        API->>JWT: Access / Refresh Token 생성
        JWT-->>Client: Access Token — Response Body
        JWT-->>Client: Refresh Token — HttpOnly Cookie
    else 인증 실패
        Security-->>Client: 401 Unauthorized
    end
```

| Token | 저장 위치 | 용도 | 유효기간 |
|---|---|---|---|
| **Access Token** | Client | API 요청 인증 | 짧게 |
| **Refresh Token** | `HttpOnly` Cookie | Access Token 재발급 | 길게 |

## 2. 인증된 API 요청

```mermaid
sequenceDiagram
    autonumber
    participant Client
    participant Filter as JWT Authentication Filter
    participant JWT as TokenProvider
    participant Context as SecurityContext
    participant Authz as Authorization
    participant API as Board API

    Client->>Filter: Authorization: Bearer {accessToken}
    Filter->>JWT: Token 검증

    alt VALID
        JWT-->>Filter: userId + Role
        Filter->>Context: Authentication 저장
        Context->>Authz: 인증 정보 전달

        alt 권한 있음
            Authz->>API: 요청 전달
            API-->>Client: 200 OK
        else 권한 없음
            Authz-->>Client: 403 Forbidden
        end
    else EXPIRED / INVALID
        Filter-->>Client: 401 Unauthorized
    end
```

## 3. Access Token 재발급

```mermaid
sequenceDiagram
    autonumber
    participant Client
    participant API as Refresh API
    participant JWT as TokenProvider

    Client->>API: POST /api/tokens/refresh<br/>Refresh Cookie 자동 전송
    API->>JWT: Refresh Token 검증

    alt Refresh Token 유효
        JWT-->>API: 새 Access Token + 새 Refresh Token
        API-->>Client: Token Pair 갱신
        Client->>Client: 실패했던 API 재요청
    else 만료 또는 위조
        API-->>Client: 401 Unauthorized
        Client->>Client: Token 제거 → 로그인 이동
    end
```

## 4. 인가 — 누가 무엇을 할 수 있는가

```mermaid
flowchart TD
    Q["게시글 수정 · 삭제 요청"] --> L{"로그인했는가?"}
    L -->|"No"| E401["401 Unauthorized"]
    L -->|"Yes"| B["게시글 조회"]
    B --> O{"Principal.userId<br/>== Board.userId ?"}
    O -->|"Yes"| OK["수정 · 삭제 수행"]
    O -->|"No"| E403["403 Forbidden"]
```

> 작성자 `userId`는 Request Body에서 받지 않는다.  
> 검증된 JWT의 `Principal`에서 꺼내 저장하고 비교한다.

| API | 비회원 | 로그인 사용자 | 작성자 본인 |
|---|:---:|:---:|:---:|
| 게시글 목록·상세·검색 | ✅ | ✅ | ✅ |
| 게시글 작성 | ❌ | ✅ | ✅ |
| 댓글 작성 | ❌ | ✅ | ✅ |
| 게시글 수정·삭제 | ❌ | ❌ | ✅ |

| 결과 | HTTP Status |
|---|:---:|
| Token 누락·만료·위조 | `401 Unauthorized` |
| 로그인했지만 작성자가 아님 | `403 Forbidden` |

## 5. ERD

```mermaid
erDiagram
    MEMBER ||--o{ BOARD : writes
    MEMBER ||--o{ COMMENT : writes
    BOARD ||--o{ COMMENT : contains

    MEMBER {
        BIGINT id PK
        VARCHAR user_id UK
        VARCHAR password_hash
        VARCHAR user_name
        VARCHAR role
    }

    BOARD {
        BIGINT id PK
        VARCHAR user_id FK
        VARCHAR title
        TEXT content
        VARCHAR file_path
        DATETIME created
    }

    COMMENT {
        BIGINT id PK
        BIGINT board_id FK
        VARCHAR user_id FK
        TEXT content
        DATETIME created
    }
```

## 적용 기술

`Spring Boot` · `Spring Security` · `JWT` · `Spring Data JPA` · `QueryDSL` · `MySQL`
