package com.ivan.curso.springboot.app.aop.springboot_aop.aop;


import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class GreetingServicesPointcut {

    @Pointcut("execution(* com.ivan.curso.springboot.app.aop.springboot_aop.services.GreetingService.*(..))")
    public void greetingFooLoggerPointcut() {
    }

    @Pointcut("execution(* com.ivan.curso.springboot.app.aop.springboot_aop.services.GreetingService.*(..))")
    public void greetingLoggerPointcut() {
    }

}
