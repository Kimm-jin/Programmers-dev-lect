package org.example.springboot.report_33_AOP;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.jspecify.annotations.Nullable;

public class ExceptionLoggingAdvice implements MethodInterceptor {
    @Override
    public @Nullable Object invoke(MethodInvocation invocation) throws Throwable {
        try {
            return invocation.proceed();
        } catch (Throwable e) {
            String name = invocation.getMethod().getDeclaringClass().getSimpleName()
                    + "." + invocation.getMethod().getName();
            System.out.println("[EXCEPTION] " + name +" : " +
                    e.getClass().getSimpleName() + " - " +e.getMessage());
            throw e;
        }
    }
}
