package com.krushit.controller.validator;

import com.krushit.dto.DistanceCalculatorDTO;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class DistanceCalculatorValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return DistanceCalculatorDTO.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        DistanceCalculatorDTO dto = (DistanceCalculatorDTO) target;

        if (dto == null) {
            errors.reject("distance.calculator.null");
            return;
        }

        if (dto.getFrom() <= 0) {
            errors.rejectValue("from", "distance.calculator.from.invalid", "From location ID must be positive and non-zero.");
        }

        if (dto.getTo() <= 0) {
            errors.rejectValue("to", "distance.calculator.to.invalid", "To location ID must be positive and non-zero.");
        }

        if (dto.getFrom() == dto.getTo() && dto.getFrom() > 0) {
            errors.reject("distance.calculator.sameLocations", "From and To location IDs cannot be the same.");
        }
    }
}
