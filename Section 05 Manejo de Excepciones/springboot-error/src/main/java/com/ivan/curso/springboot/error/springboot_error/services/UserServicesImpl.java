package com.ivan.curso.springboot.error.springboot_error.services;

import com.ivan.curso.springboot.error.springboot_error.exeptions.UserNotFoundException;
import com.ivan.curso.springboot.error.springboot_error.models.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class UserServicesImpl implements UserServices {


    private final List<User> users;

    public UserServicesImpl(List<User> users) {
        this.users = users;
    }

    @Override
    public List<User> findAll() {
        return users;
    }

    @Override
    public Optional<User> userFindById(Long id) {
        return users.stream().filter(u -> u.getId().equals(id)).findFirst();
    }

}
