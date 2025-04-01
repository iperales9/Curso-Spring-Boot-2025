package com.ivan.curso.springboot.app.aop.springboot_aop.aop;


import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Order(2)
@Aspect
@Component
public class GreetingAspect {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Before("GreetingServicesPointcut.greetingLoggerPointcut()")
    // Pointcut
    public void loggerBefore(JoinPoint joinPoint) {

        String methodName = joinPoint.getSignature().getName();
        String args = Arrays.toString(joinPoint.getArgs());

        logger.info("Antes: {} con argumentos: {}", methodName, args);

    }

    @After("GreetingServicesPointcut.greetingLoggerPointcut()")
    // Pointcut
    public void loggerAfter(JoinPoint joinPoint) {

        String methodName = joinPoint.getSignature().getName();
        String args = Arrays.toString(joinPoint.getArgs());

        logger.info("Despues: {} con argumentos: {}", methodName, args);

    }

    @AfterReturning("GreetingServicesPointcut.greetingLoggerPointcut()")
    // Pointcut
    public void loggerAfterReturning(JoinPoint joinPoint) {

        String methodName = joinPoint.getSignature().getName();
        String args = Arrays.toString(joinPoint.getArgs());
        //Format specifiers should be used instead of string concatenation.
        logger.info("Despues de retornar: {} con argumentos: {}", methodName, args);


    }

    @AfterThrowing("GreetingServicesPointcu.greetingLoggerPointcut()")
    // Pointcut
    public void loggerAfterThrowing(JoinPoint joinPoint) {

        String methodName = joinPoint.getSignature().getName();
        String args = Arrays.toString(joinPoint.getArgs());

        logger.info("Despues de lanzar una excepción: {} con argumentos: {}", methodName, args);

    }

    @Around("GreetingServicesPointcut.greetingLoggerPointcut()")
    public Object longgerAround(ProceedingJoinPoint joinPoint) throws Throwable {

        String methodName = joinPoint.getSignature().getName();
        String args = Arrays.toString(joinPoint.getArgs());
        try {
            logger.info("Antes de ejecutar: {} con argumentos: {}", methodName, args);

            Object result = joinPoint.proceed();

            logger.info("Despues de ejecutar: {} retorna el resultado: {}", methodName, result);

            return result;
        } catch (Throwable e) {
            logger.error("Error en el método: {} con argumentos: {}", methodName, args);
            throw e;
        }

    }

}
