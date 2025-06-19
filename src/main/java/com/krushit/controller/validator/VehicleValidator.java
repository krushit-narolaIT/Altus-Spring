package com.krushit.controller.validator;

import com.krushit.common.enums.FuelType;
import com.krushit.common.enums.Transmission;
import com.krushit.entity.Vehicle;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.time.LocalDateTime;

@Component
public class VehicleValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return Vehicle.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Vehicle vehicle = (Vehicle) target;

        if (vehicle.getBrandModel() == null || vehicle.getBrandModel().getBrandModelId() <= 0) {
            errors.rejectValue("brandModel", "vehicle.brandModel.invalid");
        }

        if (isNullOrEmpty(vehicle.getRegistrationNumber())) {
            errors.rejectValue("registrationNumber", "vehicle.registrationNumber.required");
        } else if (!vehicle.getRegistrationNumber().matches("^[A-Z]{2}\\d{2}[A-Z]{2}\\d{4}$")) {
            errors.rejectValue("registrationNumber", "vehicle.registrationNumber.invalid");
        }

        int currentYear = LocalDateTime.now().getYear();
        if (vehicle.getYear() < 1990 || vehicle.getYear() > currentYear) {
            errors.rejectValue("year", "vehicle.year.invalid");
        }

        if (!FuelType.isValidFuelType(vehicle.getFuelType())) {
            errors.rejectValue("fuelType", "vehicle.fuelType.invalid");
        }

        if (!Transmission.isValidTransmission(vehicle.getTransmission())) {
            errors.rejectValue("transmission", "vehicle.transmission.invalid");
        }

        if (vehicle.getGroundClearance() <= 0) {
            errors.rejectValue("groundClearance", "vehicle.groundClearance.invalid");
        }

        if (vehicle.getWheelBase() <= 0) {
            errors.rejectValue("wheelBase", "vehicle.wheelBase.invalid");
        }
    }

    private boolean isNullOrEmpty(Object value) {
        return value == null || String.valueOf(value).trim().isEmpty();
    }
}
