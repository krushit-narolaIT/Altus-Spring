package com.krushit.controller.validator;

import com.krushit.dto.BrandModelRequestDTO;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.time.LocalDateTime;

@Component
public class BrandModelValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return BrandModelRequestDTO.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        BrandModelRequestDTO model = (BrandModelRequestDTO) target;

        if (model.getServiceId() <= 0) {
            errors.rejectValue("serviceId", "vehicleModel.serviceId.invalid");
        }

        if (isNullOrEmpty(model.getBrandName())) {
            errors.rejectValue("brandName", "vehicleModel.brandName.required");
        }

        if (isNullOrEmpty(model.getModel())) {
            errors.rejectValue("model", "vehicleModel.model.required");
        }

        int currentYear = LocalDateTime.now().getYear();
        if (model.getMinYear() < 1990 || model.getMinYear() > currentYear) {
            errors.rejectValue("minYear", "vehicleModel.minYear.invalid");
        }
    }

    private boolean isNullOrEmpty(Object value) {
        return value == null || String.valueOf(value).trim().isEmpty();
    }
}
