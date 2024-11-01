package com.example.taskmanagement.validators;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.lang.reflect.Field;

public class PasswordsMatchValidator implements ConstraintValidator<PasswordsMatch, Object> {

    @Override
    public void initialize(PasswordsMatch constraintAnnotation) {
    }

    @Override
    public boolean isValid(Object obj, ConstraintValidatorContext context) {
        try {
            Field passwordField = obj.getClass().getDeclaredField("password");
            Field repeatedPasswordField = obj.getClass().getDeclaredField("repeatedPassword");

            passwordField.setAccessible(true);
            repeatedPasswordField.setAccessible(true);

            String password = (String) passwordField.get(obj);
            String repeatedPassword = (String) repeatedPasswordField.get(obj);

            return password != null && password.equals(repeatedPassword);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
            return false;
        }
    }

}
