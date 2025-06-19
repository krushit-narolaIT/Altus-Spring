package com.krushit.service;

import com.krushit.common.Message;
import com.krushit.common.enums.DocumentVerificationStatus;
import com.krushit.common.exception.ApplicationException;
import com.krushit.common.exception.DBException;
import com.krushit.dao.DriverDAOImpl;
import com.krushit.dao.IDriverDAO;
import com.krushit.dao.IVehicleDAO;
import com.krushit.dao.VehicleDAOImpl;
import com.krushit.dto.DriverDTO;
import com.krushit.dto.DriverVerificationRequestDTO;
import com.krushit.dto.PendingDriverDTO;
import com.krushit.entity.Driver;
import com.krushit.entity.User;
import com.krushit.entity.Vehicle;
import jakarta.servlet.http.Part;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class DriverService {
    private static final String STORAGE_PATH = "D:\\Project\\AltusDriverLicences";

    private final IVehicleDAO vehicleDAO = new VehicleDAOImpl();
    private final IDriverDAO driverDAO = new DriverDAOImpl();
    private final UserService userService = new UserService();

    public void storeDriverDetails(Driver driver) throws ApplicationException {
        Optional<User> userOpt = userService.getUserDetails(driver.getUser().getUserId());
        if (!userOpt.isPresent()) {
            throw new ApplicationException(Message.User.USER_NOT_FOUND);
        }
        if (driverDAO.isLicenseNumberExist(driver.getLicenceNumber())) {
            throw new ApplicationException(Message.Driver.LICENCE_NUMBER_IS_ALREADY_EXIST);
        }
        if (driverDAO.isDocumentUnderReview(driver.getDriverId()) == DocumentVerificationStatus.PENDING) {
            throw new ApplicationException(Message.Driver.DOCUMENT_IS_UNDER_REVIEW);
        }
        Driver updatedDriver = new Driver.DriverBuilder()
                .setLicenceNumber(driver.getLicenceNumber())
                .setLicencePhoto(driver.getLicencePhoto())
                .setVerificationStatus(DocumentVerificationStatus.PENDING)
                .setUser(new User.UserBuilder().setUserId(driver.getUser().getUserId()).build())
                .build();
        driverDAO.insertDriverDetails(updatedDriver);
    }

    public String storeLicencePhoto(Part filePart, String licenceNumber, String displayId) throws ApplicationException {
        try {
            File file = new File(STORAGE_PATH);
            if (!file.exists()) {
                file.mkdirs();
            }
            String originalFileName = filePart.getSubmittedFileName();
            String extension = originalFileName.substring(originalFileName.lastIndexOf("."));
            String fileName = "DRI_" + licenceNumber + "_" + displayId + extension;
            Path path = Paths.get(STORAGE_PATH, fileName);
            try (InputStream input = filePart.getInputStream()) {
                Files.copy(input, path, StandardCopyOption.REPLACE_EXISTING);
            }
            return path.toString();
        } catch (IOException e) {
            throw new DBException(Message.Driver.FAILED_TO_STORE_DOCUMENT + e.getMessage());
        }
    }

    public List<PendingDriverDTO> getPendingVerificationDrivers() throws DBException {
        return driverDAO.getDriversWithPendingVerification();
    }

    public boolean isDriverExist(int driverId) throws ApplicationException {
        return driverDAO.isDriverExist(driverId);
    }

    public boolean isDocumentExist(int driverId) throws ApplicationException {
        return driverDAO.isDocumentExist(driverId);
    }

    public void verifyDriver(DriverVerificationRequestDTO verificationRequestDTO, int driverId) throws ApplicationException {
        if (!isDriverExist(driverId)) {
            throw new ApplicationException(Message.DRIVER_NOT_EXIST);
        }
        if(!isDocumentExist(driverId)){
            throw new ApplicationException(Message.Driver.DOCUMENT_NOT_UPLOADED);
        }
        if(DocumentVerificationStatus.VERIFIED.name().equalsIgnoreCase(verificationRequestDTO.getVerificationStatus())){
            driverDAO.updateDriveVerificationDetail(driverId, true, null);
        } else if(DocumentVerificationStatus.REJECTED.name().equalsIgnoreCase(verificationRequestDTO.getVerificationStatus())) {
            driverDAO.updateDriveVerificationDetail(driverId, false, verificationRequestDTO.getMessage());
        } else {
            throw new ApplicationException(Message.Driver.PLEASE_PERFORM_VALID_VERIFICATION_OPERATION);
        }
    }

    public List<DriverDTO> getAllDrivers() throws ApplicationException {
        List<Driver> drivers = driverDAO.getAllDrivers();
        return drivers.stream()
                .map(driver -> {
                    User user = driver.getUser();
                    return new DriverDTO.DriverDTOBuilder()
                            .setUserId(user.getUserId())
                            .setRole(user.getRole().getRoleType())
                            .setFirstName(user.getFirstName())
                            .setLastName(user.getLastName())
                            .setPhoneNo(user.getPhoneNo())
                            .setEmailId(user.getEmailId())
                            .setDisplayId(user.getDisplayId())
                            .setLicenceNumber(driver.getLicenceNumber())
                            .setLicencePhoto(driver.getLicencePhoto())
                            .setDocumentVerified(driver.isDocumentVerified())
                            .build();
                })
                .collect(Collectors.toList());
    }

    public void addVehicle(Vehicle vehicle, int userId) throws ApplicationException {
        Driver driver = driverDAO.getDriver(userId)
                .orElseThrow(() -> new ApplicationException(Message.Driver.NO_DRIVERS_FOUND));
        if (!driverDAO.isDocumentExist(driver.getDriverId())) {
            throw new ApplicationException(Message.Driver.DOCUMENT_NOT_UPLOADED);
        }
        if (!driverDAO.isDocumentVerified(driver.getDriverId())) {
            throw new ApplicationException(Message.Driver.DOCUMENT_NOT_VERIFIED);
        }
        if (vehicleDAO.isDriverVehicleExist(driver.getDriverId())) {
            throw new ApplicationException(Message.Vehicle.DRIVER_VEHICLE_ALREADY_EXIST);
        }
        if (vehicleDAO.isBrandModelExistsByID(vehicle.getVehicleId())) {
            throw new ApplicationException(Message.Vehicle.BRAND_MODEL_NOT_SUPPORTED);
        }
        int minYear = vehicleDAO.getMinYearForBrandModel(vehicle.getBrandModel().getBrandModelId());
        if (vehicle.getYear() < minYear) {
            throw new ApplicationException(Message.Vehicle.BRAND_MODEL_YEAR_NOT_SUPPORTED);
        }
        vehicle.setDriver(driver);
        driverDAO.updateDriverAvailability(driver.getDriverId());
        vehicleDAO.addVehicle(vehicle);
    }

    public void deleteVehicle(int userId) throws ApplicationException {
        Driver driver = driverDAO.getDriver(userId)
                .orElseThrow(() -> new DBException(Message.Driver.NO_DRIVERS_FOUND));
        if (!vehicleDAO.isDriverVehicleExist(driver.getDriverId())) {
            throw new ApplicationException(Message.Vehicle.VEHICLE_NOT_EXIST);
        }
        vehicleDAO.deleteVehicleByUserId(userId);
    }

    public int getDriverIdFromUserId(int userId) throws ApplicationException{
        Driver driver = driverDAO.getDriver(userId)
                .orElseThrow(() -> new DBException(Message.Driver.NO_DRIVERS_FOUND));
        return driver.getDriverId();

    }
}