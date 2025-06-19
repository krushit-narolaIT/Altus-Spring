package com.krushit.controller.validator;

import com.krushit.common.Message;
import com.krushit.common.enums.DocumentVerificationStatus;
import com.krushit.dto.DriverVerificationRequestDTO;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class DriverVerificationRequestValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return DriverVerificationRequestDTO.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        DriverVerificationRequestDTO request = (DriverVerificationRequestDTO) target;

        if (request == null) {
            errors.reject("verificationRequest.null", Message.Vehicle.PLEASE_ENTER_VALID_VERIFICATION_REQUEST);
            return;
        }

        String status = request.getVerificationStatus();
        if (status == null || status.trim().isEmpty()) {
            errors.rejectValue("verificationStatus", "verificationStatus.empty", Message.Vehicle.PLEASE_ENTER_VALID_VERIFICATION_STATUS);
        } else if (!status.equalsIgnoreCase(DocumentVerificationStatus.VERIFIED.name())
                && !status.equalsIgnoreCase(DocumentVerificationStatus.REJECTED.name())) {
            errors.rejectValue("verificationStatus", "verificationStatus.invalid", Message.Vehicle.PLEASE_ENTER_VALID_VERIFICATION_STATUS);
        }

        if (DocumentVerificationStatus.REJECTED.name().equalsIgnoreCase(status)) {
            if (request.getMessage() == null || request.getMessage().trim().isEmpty()) {
                errors.rejectValue("message", "message.required", Message.Vehicle.PLEASE_ENTER_REJECTION_MESSAGE);
            }
        }
    }
}
