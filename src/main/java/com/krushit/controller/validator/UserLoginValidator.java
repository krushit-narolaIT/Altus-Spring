package com.krushit.controller.validator;

import com.krushit.common.enums.RoleType;
import com.krushit.entity.User;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class UserLoginValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return User.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "emailId", "emailId.empty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "password.empty");
    }
}
