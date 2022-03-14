package com.starwars.StarWarsAPI.utils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class GeneroValidator implements ConstraintValidator<Genero, String> {

    @Override
    public void initialize(Genero constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        for (GeneroEnum c : GeneroEnum.values()) {
            if (c.name().equals(value.toUpperCase())) {
                return true;
            }
        }
        return false;
    }
}
