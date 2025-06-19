package com.krushit.controller.validator;

import com.krushit.common.enums.VehicleType;
import com.krushit.dto.VehicleServiceDTO;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.math.BigDecimal;

@Component
public class VehicleServiceValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return VehicleServiceDTO.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        VehicleServiceDTO service = (VehicleServiceDTO) target;

        if (isNullOrEmpty(service.getServiceName())) {
            errors.rejectValue("serviceName", "vehicleService.serviceName.required");
        }

        if (service.getBaseFare() == null || service.getBaseFare().compareTo(BigDecimal.ZERO) < 0) {
            errors.rejectValue("baseFare", "vehicleService.baseFare.invalid");
        }

        if (service.getPerKmRate() == null || service.getPerKmRate().compareTo(BigDecimal.ZERO) < 0) {
            errors.rejectValue("perKmRate", "vehicleService.perKmRate.invalid");
        }

        if (!VehicleType.isValidVehicleType(service.getVehicleType())) {
            errors.rejectValue("vehicleType", "vehicleService.vehicleType.invalid");
        }

        if (service.getMaxPassengers() <= 0 || service.getMaxPassengers() > 8) {
            errors.rejectValue("maxPassengers", "vehicleService.maxPassengers.invalid");
        }

        if (service.getCommissionPercentage() == null ||
                service.getCommissionPercentage().compareTo(BigDecimal.ZERO) < 0 ||
                service.getCommissionPercentage().compareTo(BigDecimal.valueOf(100)) > 0) {
            errors.rejectValue("commissionPercentage", "vehicleService.commissionPercentage.invalid");
        }
    }

    private boolean isNullOrEmpty(Object value) {
        return value == null || String.valueOf(value).trim().isEmpty();
    }
}
