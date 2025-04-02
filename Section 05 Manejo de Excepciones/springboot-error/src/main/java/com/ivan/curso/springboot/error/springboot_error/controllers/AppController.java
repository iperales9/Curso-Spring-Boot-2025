package com.ivan.curso.springboot.error.springboot_error.controllers;

import com.ivan.curso.springboot.error.springboot_error.exeptions.UserNotFoundException;
import com.ivan.curso.springboot.error.springboot_error.models.domain.User;
import com.ivan.curso.springboot.error.springboot_error.services.UserServicesImpl;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class AppController {

    private final UserServicesImpl userServices;

    public AppController(UserServicesImpl userServices) {
        this.userServices = userServices;
    }


    // Error division por zero
    @GetMapping("/test1")
    public String index() {
        int value = 100 / 0;
        System.out.println(value);
        return "ok 200";
    }

    // Error en el formato de los datos
    @GetMapping("/test2")
    public String test2() {
        int value = Integer.parseInt("10x");
        System.out.println(value);
        return "ok 200";
    }

    @GetMapping("/show/{id}")
    public User show(@PathVariable("id") Long id) {
        return userServices.userFindById(id).orElseThrow(() -> new UserNotFoundException("No se ha encontrado el usuario con el id: " + id));
    }

}
