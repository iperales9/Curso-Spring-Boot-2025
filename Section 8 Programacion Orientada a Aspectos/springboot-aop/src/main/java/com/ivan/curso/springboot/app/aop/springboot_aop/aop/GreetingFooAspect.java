package com.ivan.curso.springboot.app.aop.springboot_aop.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Order(1)
@Aspect
@Component
public class GreetingFooAspect {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Before("GreetingServicesPointcut.greetingFooLoggerPointcut()")
    // Pointcut
    public void loggerBefore(JoinPoint joinPoint) {

        String methodName = joinPoint.getSignature().getName();
        String args = Arrays.toString(joinPoint.getArgs());

        logger.info("Antes foo: {} invocado con los parametros: {}", methodName, args);

    }

    @After("GreetingServicesPointcut.greetingFooLoggerPointcut()")
    // Pointcut
    public void loggerAfter(JoinPoint joinPoint) {

        String methodName = joinPoint.getSignature().getName();
        String args = Arrays.toString(joinPoint.getArgs());

        logger.info("Despues foo: {} con los parametros : {}", methodName, args);

    }

}
