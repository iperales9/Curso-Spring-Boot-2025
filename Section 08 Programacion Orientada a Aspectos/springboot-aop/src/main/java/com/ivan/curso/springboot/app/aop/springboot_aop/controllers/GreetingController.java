package com.ivan.curso.springboot.app.aop.springboot_aop.controllers;

import com.ivan.curso.springboot.app.aop.springboot_aop.services.GreetingService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;

@RestController
@RequestMapping("/api/v1")
public class GreetingController {

    private GreetingService greetingService;

    public GreetingController(GreetingService greetingService) {
        this.greetingService = greetingService;
    }


    @GetMapping("/greeting")
    public ResponseEntity<?> greeting() {
        return ResponseEntity.ok(
                Collections.singletonMap
                        ("greeting", greetingService.sayHello
                                ("Pepe","How are you?")));
    }

    @GetMapping("/greeting-error")
    public ResponseEntity<?> greeting2() {
        return ResponseEntity.ok(
                Collections.singletonMap
                        ("greeting", greetingService.sayHello2
                                ("Pepe","How are you?")));
    }

}
