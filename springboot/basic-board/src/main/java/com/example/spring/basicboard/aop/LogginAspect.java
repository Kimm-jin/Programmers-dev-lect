package com.example.spring.basicboard.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

// * @Aspect
// - "이 클래스는 공통 기능(횡단 관심사)을 모아둔 Aspect다"라고 선언하는 어노테이션이다.
// - 이 어노테이션이 붙어야 스프링 AOP가 이 클래스 안의 포인트컷/어드바이스를 인식한다.
// - "AOP" 규칙을 담고 있다"는 표시일 뿐, 스프링이 관리하는 빈으로 등록해주지 않는다.
// - 스프링 컨테이너에 빈으로 등록해야, 스프링이 이 Aspect를 찾아서 실제로 적용된다.
// -> Component 어노테이션을 쓰는 이유
@Aspect
@Component
public class LogginAspect {

    // * Pointcut : "어디에" 적용할지 정의한다.
    // 표현식 해석 : execution(* com.example.spring.basicboard.controller..*.*(..))
    // - execution : 메서드 "실행" 지점을 대상으로 한다는 지시어
    // - * : 반환 타입은 무엇이든(모든 타입) 상관없다.
    // - com...controller.. : controller 패키지와 그 하위 패키지 전부
    // - * : 그 안의 모든 클래스의 모든 메서드
    // - (..) : 메서드 파라미터는 개수/타입 상관없이 모두
    // -> "controller 패키지 아래 모든 메서드"를 대상으로 삼겠다는 뜻
    @Pointcut("execution(* com.example.spring.basicboard.controller..*.*(..))")
    public void controllerLog(){
        // 메서드 본문(body)은 비워둔다. 실제 로직이 아니라, "대상을 가르키는 이름표" 역할만 하기 때문
    }
}

// @Around : "언제/무엇을"할지 정의하는 어드바이스
// 어드바이스에는 5가지 종류가 있다.
// - @Before -          : 대상 메서드 실행 "직전"에만 실행
// - @AfterReturning    : 대상 메서드가 "정상 반환된 후" 실행
// - @AfterThrowing     : 대상 메서드가 "예외를 던졌을 때" 실행
// - @After             : 정상/예외 상관없이 "끝나면 항상"실행
// - @Around            : 대상 메서드 실행을 "통째로 감싼다". 전/후/예외를 모두 한 메서드에서 제어 가능
@Around("controllerLog()")
public Object logRequest(ProceedingJoinPoint joinPoint) throws Throwable{
    return null;
}
