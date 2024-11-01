package com.example.taskmanagement.validators;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.passay.AllowedRegexRule;
import org.passay.PasswordData;
import org.passay.PasswordValidator;
import org.passay.RuleResult;

import java.util.List;

public class EmailValidator implements ConstraintValidator<ValidEmail, String> {

    private static final String EMAIL_REGEX =
            "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";

    @Override
    public boolean isValid(String email, ConstraintValidatorContext context) {
        if (email == null || email.isEmpty()) return false;
        PasswordValidator validator = new PasswordValidator(List.of(new AllowedRegexRule(EMAIL_REGEX)));
        RuleResult result = validator.validate(new PasswordData(email));

//        if (!result.isValid()) {
//            context.disableDefaultConstraintViolation();
//            context.buildConstraintViolationWithTemplate(
//                            String.join(",", validator.getMessages(result)))
//                    .addConstraintViolation();
//        }

        if (!result.isValid()) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("Invalid email").addConstraintViolation();
        }

        return result.isValid();
    }

}
