package com.krushit.controller.validator;

import com.krushit.common.Message;
import com.krushit.dto.DriverDocumentDTO;
import jakarta.servlet.http.Part;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class DriverDocumentValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return DriverDocumentDTO.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        DriverDocumentDTO dto = (DriverDocumentDTO) target;

        String licenceNumber = dto.getLicenceNumber();
        if (licenceNumber == null || licenceNumber.trim().isEmpty()) {
            errors.rejectValue("licenceNumber", "licenceNumber.empty", Message.Driver.LICENCE_NUMBER_IS_REQUIRED);
        } else if (licenceNumber.length() != 15 || !licenceNumber.matches("^[A-Za-z0-9]+$")) {
            errors.rejectValue("licenceNumber", "licenceNumber.invalid", Message.Driver.ENTER_VALID_LICENCE_NUMBER);
        }

        Part photo = dto.getLicencePhoto();
        if (photo == null || photo.getSize() == 0) {
            errors.rejectValue("licencePhoto", "licencePhoto.empty", Message.Driver.LICENCE_PHOTO_PATH_IS_REQUIRD);
        } else {
            String fileName = photo.getSubmittedFileName();
            if (fileName == null || fileName.isEmpty()) {
                errors.rejectValue("licencePhoto", "licencePhoto.nameMissing", Message.Driver.UPLOAD_VALID_LICENCE_PHOTO);
            } else {
                String fileExtension = fileName.substring(fileName.lastIndexOf('.') + 1).toLowerCase();
                if (!fileExtension.matches("jpg|jpeg|png")) {
                    errors.rejectValue("licencePhoto", "licencePhoto.invalidType", Message.Driver.INVALID_FILE_TYPE);
                }
            }
        }
    }
}
