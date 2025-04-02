package com.ivan.curso.springboot.app.aop.springboot_aop.services;

import org.springframework.stereotype.Service;

@Service
public class GreetingServiceImpl implements GreetingService {

    @Override
    public String sayHello(String person, String message) {
        System.out.println("greeting() method called");
        return "Hello " + person + ", " + message;
    }

    @Override
    public String sayHello2(String person, String message) {
        throw new RuntimeException("any error");
    }


}
