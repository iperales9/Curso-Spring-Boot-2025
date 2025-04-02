package com.ivan.curso.springboot.webapp.springboot_web.controllers;

import com.ivan.curso.springboot.webapp.springboot_web.models.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.Arrays;
import java.util.List;

@Controller
public class UserController {

    @GetMapping("/details")
    public String details(Model model) {

        User user = new User("Iván", "Perales López");
        user.setEmail("ivan@gmail.com");

        model.addAttribute("title", "Hola mundo Spring Boot");
        model.addAttribute("user", user);

        return "details";
    }

    @GetMapping("/list")
    public ModelMap list(ModelMap model) {

        model.addAttribute("title", "Listado de usuarios");
        return model;

    }

    @ModelAttribute("users")
    public List<User> userModel() {

        return Arrays.asList(
                new User("Iván", "Perales", "ivan@gmail.com"),
                new User("Pepe", "López"),
                new User("Juan", "Castillo", "juan@gmail.com"),
                new User("Maria", "Sucias"));
    }

}
