package org.example.springboot.report_33_AOP;

import org.jspecify.annotations.Nullable;
import org.springframework.aop.ClassFilter;
import org.springframework.aop.MethodMatcher;
import org.springframework.aop.Pointcut;

import java.lang.reflect.Method;

public class GetMethodPointcut implements Pointcut {
    @Override
    public ClassFilter getClassFilter() {
        return clazz -> clazz.getPackageName()
                .startsWith("org.example.springboot.report_33_AOP.service");
    }

    @Override
    public MethodMatcher getMethodMatcher() {
        return new MethodMatcher() {
            @Override
            public boolean matches(Method method, Class<?> targetClass) {
                return method.getName().startsWith("get");
            }

            @Override
            public boolean isRuntime() {
                return false;
            }

            @Override
            public boolean matches(Method method, Class<?> targetClass, @Nullable Object... args) {
                return true;
            }
        };
    }
}
