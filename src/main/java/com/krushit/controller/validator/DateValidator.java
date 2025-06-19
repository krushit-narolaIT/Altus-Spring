package com.krushit.controller.validator;

import com.krushit.common.Message;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

@Component
public class DateValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return String.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        String dateStr = (String) target;

        if (dateStr == null || dateStr.trim().isEmpty()) {
            errors.reject("date.empty", Message.Ride.PLEASE_ENTER_VALID_DATE);
            return;
        }

        try {
            LocalDate date = LocalDate.parse(dateStr);
            if (date.isAfter(LocalDate.now())) {
                errors.reject("date.future", Message.Ride.DATE_CANNOT_BE_IN_FUTURE);
            }
        } catch (DateTimeParseException e) {
            errors.reject("date.format.invalid", Message.Ride.INVALID_DATE_FORMAT);
        }
    }
}
