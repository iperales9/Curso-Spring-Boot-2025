package com.ivan.curso.springboot.error.springboot_error;

import com.ivan.curso.springboot.error.springboot_error.models.domain.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class ApiConfig {

    @Bean
    public List<User> users() {
        return List.of(
                new User(1L, "Ivan", "Lopez"),
                new User(2L, "John", "Doe"),
                new User(3L, "Jane", "Doe"),
                new User(4L, "Tina", "Doe"));
    }


}
