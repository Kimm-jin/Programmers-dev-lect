package org.example.springboot.report_33_AOP;

import org.example.springboot.report_33_AOP.service.*;
import org.springframework.aop.Advisor;
import org.springframework.aop.Pointcut;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AopConfig {

    @Bean // 자동 프록시 켜기
    public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator(){
        return new DefaultAdvisorAutoProxyCreator();
    }



    @Bean // Advisor = Pointcut + Advice
    public Advisor performanceAdvisor(Pointcut pointcut, PerformanceMonitorAdvice performanceMonitorAdvice ) {
        return new DefaultPointcutAdvisor(pointcut, performanceMonitorAdvice);
    }

    @Bean
    public Advisor exceptionLoggingAdvisor( Pointcut pointcut, PerformanceMonitorAdvice performanceMonitorAdvice ){
        return new DefaultPointcutAdvisor(pointcut, exceptionLoggingAdvice());
    }

    @Bean // Advice(무엇을)
    public PerformanceMonitorAdvice performanceMonitorAdvice() {
        return new PerformanceMonitorAdvice();
    }

    @Bean
    public ExceptionLoggingAdvice exceptionLoggingAdvice(){
        return new ExceptionLoggingAdvice();
    }
/*
    @Bean // Pointcut(어디에)
    public Pointcut pointcut(){
        AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
//        pointcut.setExpression("execution(* org.example.springboot.report_33_AOP.service..*.*(..))");
        pointcut.setExpression("execution(* org.example.springboot.report_33_AOP.service..*.get*(..))");
        return pointcut;
    }

 */
    @Bean
    public Pointcut pointcut(){
        return new GetMethodPointcut();
    }



    @Bean
    public OrderService orderService(){return new OrderServiceImpl();}
    @Bean
    public MemberService memberService(){return new MemberServiceImpl(); }
}