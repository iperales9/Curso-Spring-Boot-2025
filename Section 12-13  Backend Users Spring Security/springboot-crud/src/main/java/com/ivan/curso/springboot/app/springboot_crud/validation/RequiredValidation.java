package com.ivan.curso.springboot.app.springboot_crud.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.util.StringUtils;

public class RequiredValidation implements ConstraintValidator<IsRequiered, Object> {

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        if (value == null) {
            return false; // Null no es válido
        }

        if (value instanceof String) {
            return StringUtils.hasText((String) value); // No vacío ni espacios en blanco
        }

        if (value instanceof Integer) {
            return (Integer) value > 0; // Mayor que 0
        }

        return true; // Otros tipos se consideran válidos
    }
}