package org.example.springboot.report_33_AOP;


import org.example.springboot.report_33_AOP.service.*;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {
        var ctx = new AnnotationConfigApplicationContext(AopConfig.class);

        OrderService  orderService  = ctx.getBean(OrderService.class);
        MemberService memberService = ctx.getBean(MemberService.class);

        System.out.println("===== 주문 서비스 호출 =====");
        System.out.println(orderService.placeOrder("기계식 키보드"));

        System.out.println("\n===== 회원 서비스 호출 =====");
        System.out.println(memberService.register("kim"));

        System.out.println("\n===== 진짜 프록시인지 확인 =====");
        System.out.println("orderService 의 실제 타입: " + orderService.getClass());


        // Spring 빈으로 등록된 객체를 프록시가 감싸고
        // 프록시를 통해 Pointcut에 맞는 메서드를 호출해야 invoke가 실행된다.
        System.out.println("\n===== 회원 이름 확인 =====");
        System.out.println(memberService.getMember("kim"));
        System.out.println();

        try {
            System.out.println(memberService.getFailMember("kim"));
        } catch (Exception e) {
            System.out.println("예외 처리: " + e.getMessage());
        }
        ctx.close();

        /*

            자동 프록시는 target 빈을 평범하게 등록해두면
            Spring의 AutoProxyCreator가 Advisor의 Pointcut을 보고 적용 대상을 찾고
            자동으로 프록시를 만들어 Advice를 연결한다. 직접 데코레이터를 감싸던 과정이 자동화되었다.

         */
    }
}


