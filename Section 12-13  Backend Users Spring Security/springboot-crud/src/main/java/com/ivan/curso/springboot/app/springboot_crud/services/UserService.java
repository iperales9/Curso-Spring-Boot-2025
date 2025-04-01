package com.ivan.curso.springboot.app.springboot_crud.services;

import com.ivan.curso.springboot.app.springboot_crud.entities.User;

import java.util.List;

public interface UserService {

    List<User> findAll();

    User save(User user);

    boolean existsByUsername(String value);
}
