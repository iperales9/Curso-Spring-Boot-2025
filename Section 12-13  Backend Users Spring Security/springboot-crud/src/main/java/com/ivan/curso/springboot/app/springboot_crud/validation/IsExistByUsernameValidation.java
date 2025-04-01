package com.ivan.curso.springboot.app.springboot_crud.validation;

import com.ivan.curso.springboot.app.springboot_crud.services.UserService;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.stereotype.Component;

@Component
public class IsExistByUsernameValidation implements ConstraintValidator<IsExistByUsername, String> {

    private final UserService userService;

    public IsExistByUsernameValidation(UserService userService) {
        this.userService = userService;
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
        if (userService == null) return true;
        return !userService.existsByUsername(value);
    }
}
