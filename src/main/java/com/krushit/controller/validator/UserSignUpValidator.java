package com.krushit.controller.validator;

import com.krushit.common.enums.RoleType;
import com.krushit.entity.User;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class UserSignUpValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return User.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        User user = (User) target;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "firstName", "firstName.empty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "lastName", "lastName.empty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "emailId", "emailId.empty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "password.empty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "phoneNo", "phoneNo.empty");

        if (user.getEmailId() != null && !user.getEmailId().matches("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,20}$")) {
            errors.rejectValue("emailId", "emailId.invalid");
        }

        if (user.getPhoneNo() != null && !user.getPhoneNo().matches("^\\d{10}$")) {
            errors.rejectValue("phoneNo", "phoneNo.invalid");
        }

        if (user.getPassword() != null && !user.getPassword().matches("^(?=.*[A-Z])(?=.*\\d)(?=.*[@#$%^&+=!])[A-Za-z\\d@#$%^&+=!]{8,20}$")) {
            errors.rejectValue("password", "password.invalid");
        }

        if (user.getRole() == null || user.getRole().getRoleType() == null) {
            errors.rejectValue("role", "role.empty");
        } else if (!isValidRole(user.getRole().getRoleType())) {
            errors.rejectValue("role", "role.invalid");
        }
    }

    private boolean isValidRole(RoleType roleType) {
        for (RoleType type : RoleType.values()) {
            if (type.equals(roleType)) {
                return true;
            }
        }
        return false;
    }
}
