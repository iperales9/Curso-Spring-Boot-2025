package com.ivan.curso.springboot.error.springboot_error.services;

import com.ivan.curso.springboot.error.springboot_error.models.domain.User;

import java.util.List;
import java.util.Optional;

public interface UserServices {

    List<User> findAll();

    Optional<User> userFindById(Long id);

}
