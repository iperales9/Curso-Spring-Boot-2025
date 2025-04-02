package com.ivan.curso.springboot.app.interceptor.springboot_interceptor.controllers;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/app")
public class FooController {

    @GetMapping("/foo")
    public Map<String, String> foo() {
        return Map.of("message", "handler foo del controlador");
    }

    @GetMapping("/bar")
    public Map<String, String> bar() {
        return Map.of("message", "handler bar del controlador");
    }

    @GetMapping("/vaz")
    public Map<String, String> vaz() {
        return Map.of("message", "handler vaz del controlador");
    }
}
