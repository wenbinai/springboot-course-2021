package com.example.springexamples.example02.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.aop.aspectj.AspectJAroundAdvice;
import org.springframework.stereotype.Component;

@Component
@Aspect
@Slf4j
public class MyAspect {
    @Pointcut("execution(* com.example.springexamples.example02.aop..*.*(..))")
    public void pointcut(){}

    @Before("pointcut()")
    public void beforeAdvice() {
        log.debug("beforeAdvice()");
    }
    @After("pointcut()")
    public void afterAdvice() {
        log.debug("afterAdvice()");
    }

}
