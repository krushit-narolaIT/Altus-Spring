package com.krushit.dao;

import com.krushit.common.enums.DocumentVerificationStatus;
import com.krushit.common.exception.DBException;
import com.krushit.dto.PendingDriverDTO;
import com.krushit.entity.Driver;

import java.util.List;
import java.util.Optional;

public interface IDriverDAO {
    boolean isDriverExist(int driverId) throws DBException;
    void updateDriveVerificationDetail(int driverId, boolean isVerified, String rejectionMessage) throws DBException;
    List<Driver> getAllDrivers() throws DBException;
    void insertDriverDetails(Driver driver) throws DBException;
    List<PendingDriverDTO> getDriversWithPendingVerification() throws DBException;
    Optional<Driver> getDriver(int userId) throws DBException;
    boolean isDocumentVerified(int driverId) throws DBException;
    boolean isDocumentExist(int driverId) throws DBException;
    boolean isLicenseNumberExist(String licenseNumber) throws DBException;
    DocumentVerificationStatus isDocumentUnderReview(int driverId) throws DBException;
    void updateDriverAvailability(int driverId) throws DBException;
}
