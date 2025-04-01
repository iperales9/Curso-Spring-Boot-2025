package com.ivan.curso.springboot.app.springboot_crud.validation;

import com.ivan.curso.springboot.app.springboot_crud.services.ProductService;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.stereotype.Component;

@Component
public class IsExistDbValidation implements ConstraintValidator<IsExistDb, String> {

    private final ProductService service;

    public IsExistDbValidation(ProductService service) {
        this.service = service;
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {

        if (service == null) return true;

        return value != null && !service.existBySku(value);
    }

}
