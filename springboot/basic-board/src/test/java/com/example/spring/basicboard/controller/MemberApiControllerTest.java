package com.example.spring.basicboard.controller;

// * 프레젠테이션 계층 테스트 - 컨트롤러의 "Http 계약"을 검증한다.

// 무엇을 보나?
// URL 매핑, 요청 본문(JSON) 파싱, 상태 코드, 응답 JSON, 예외 -> 상태 코드 변화
// "비지니스 로직"이 아니라, "웹 껍데기가 제대로 동작하는가"가 관심사다.

// @WebMvcTest
// - 웹 계층(컨트롤러, @RestControllerAdvice 등)만 뜨는 슬라이스 테스트다 (서비스/레포지토리/DB는 안뜬다.)
// - 그래서 컨트롤러가 의존하는 MemberService는 "진짜"가 없다. -> @MockBean으로 가짜를 넣어준다
// - GlobalExceptionHandler(@RestControllerAdvice)는 웹 계층이라 자동으로 함께 로드된다. (예외 -> 응답 검증 가능)
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;

@WebMvcTest(MemberApiControllerTest.class)
class MemberApiControllerTest {

}
