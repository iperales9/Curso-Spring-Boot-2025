package com.ivan.curso.springboot.webapp.springboot_web.controllers;

import com.ivan.curso.springboot.webapp.springboot_web.models.User;
import com.ivan.curso.springboot.webapp.springboot_web.models.dto.UserDto;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class UserRestController {

    @GetMapping("/details")
    public UserDto details(Model model) {

        User user = new User("Iván", "Perales López");
        UserDto userDto = new UserDto();
        userDto.setUser(user);
        userDto.setTitle("Hola mundo Spring Boot");

        return userDto;
    }

    @GetMapping("list")
    public List<User> list() {
        User user = new User("Iván", "Perales");
        User user1 = new User("Pepe", "López");
        User user2 = new User("Juan", "Castillo");
        User user3 = new User("Maria", "Sucias");

        return List.of(user, user1, user2, user3);
    }

    @GetMapping("/details-map")
    public Map<String, Object> detailsMap(Model model) {

        Map<String, Object> body = new HashMap<>();
        User user = new User("Iván", "Perales López");

        body.put("title", "Hola mundo Spring Boot");
        body.put("user", user);

        return body;
    }

}
