package com.ivan.curso.springboot.app.springboot_crud.validation;

import com.ivan.curso.springboot.app.springboot_crud.entities.Product;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class ProductValidation implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return Product.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {

        Product product = (Product) target;
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", null, "es requerido");

        if (product.getDescription() == null || product.getDescription().isBlank()) {
            ValidationUtils.rejectIfEmptyOrWhitespace(errors, "description", null, "es requerido porfavor");
        }
        if (product.getPrice() == null) {
            ValidationUtils.rejectIfEmptyOrWhitespace(errors, "price", null, "no puede ser null ok!");
        } else if (product.getPrice() < 10) {
            ValidationUtils.rejectIfEmptyOrWhitespace(errors, "price", null, "No puede ser menor que 10");
        }

    }
}
