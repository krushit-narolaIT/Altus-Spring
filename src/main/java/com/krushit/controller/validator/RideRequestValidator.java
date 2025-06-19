package com.krushit.controller.validator;

import com.krushit.dto.RideRequestDTO;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.time.LocalDate;
import java.time.LocalTime;

@Component
public class RideRequestValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return RideRequestDTO.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        RideRequestDTO ride = (RideRequestDTO) target;

        if (ride == null) {
            errors.reject("ride.request.null");
            return;
        }

        boolean hasInvalidPickup = isNullOrEmpty(ride.getPickUpLocationId()) || ride.getPickUpLocationId() <= 0;
        boolean hasInvalidDropOff = isNullOrEmpty(ride.getDropOffLocationId()) || ride.getDropOffLocationId() <= 0;

        if (hasInvalidPickup) {
            errors.rejectValue("pickUpLocationId", "ride.pickupLocationId.invalid");
        }

        if (hasInvalidDropOff) {
            errors.rejectValue("dropOffLocationId", "ride.dropOffLocationId.invalid");
        }

        if (ride.getPickUpLocationId() == ride.getDropOffLocationId()) {
            errors.reject("ride.sameLocations");
        }

        if (isNullOrEmpty(ride.getVehicleServiceId()) || ride.getVehicleServiceId() <= 0) {
            errors.rejectValue("vehicleServiceId", "ride.vehicleServiceId.invalid");
        }

        if (isNullOrEmpty(ride.getUserId()) || ride.getUserId() <= 0) {
            errors.rejectValue("userId", "ride.userId.invalid");
        }

        if (ride.getRideDate() == null) {
            errors.rejectValue("rideDate", "ride.date.required");
        } else {
            LocalDate rideDate = ride.getRideDate();

            if (rideDate.isBefore(LocalDate.now())) {
                errors.rejectValue("rideDate", "ride.date.past");
            }

            if (rideDate.isAfter(LocalDate.now().plusDays(15))) {
                errors.rejectValue("rideDate", "ride.date.futureLimit");
            }
        }

        if (ride.getPickUpTime() == null) {
            errors.rejectValue("pickUpTime", "ride.pickupTime.required");
        } else if (ride.getRideDate() != null && ride.getRideDate().isEqual(LocalDate.now())) {
            LocalTime pickUpTime = ride.getPickUpTime();
            if (pickUpTime.isBefore(LocalTime.now())) {
                errors.rejectValue("pickUpTime", "ride.pickupTime.past");
            }
        }
    }

    private boolean isNullOrEmpty(Object value) {
        return value == null || String.valueOf(value).trim().isEmpty();
    }
}
