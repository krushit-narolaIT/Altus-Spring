package com.krushit.dao;

import com.krushit.common.enums.RideStatus;
import com.krushit.common.exception.DBException;
import com.krushit.dto.RideCancellationDetailsDTO;
import com.krushit.entity.Ride;
import com.krushit.entity.RideRequest;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

public interface IRideDAO {
    List<RideRequest> getAllMatchingRideRequests(int driverId) throws DBException;
    Optional<RideRequest> getRideRequest(int rideRequestId) throws DBException;
    Optional<Ride> getConflictingRide(int driverId, LocalDate rideDate, LocalTime pickUpTime) throws DBException;
    void createRide(int rideRequestId, Ride ride) throws DBException;
    Optional<Ride> getRide(int rideId) throws DBException;
    void updateRideCancellation(RideCancellationDetailsDTO cancellationDetails) throws DBException;
    List<Ride> getAllRidesByUserId(int userId) throws DBException;
    RideStatus getRideStatus(int rideId) throws DBException;
    List<Ride> getRidesByDateRange(int driverId, LocalDate startDate, LocalDate endDate) throws DBException;
    int getTotalRides(int driverId, LocalDate startDate, LocalDate endDate) throws DBException;
    double getTotalEarnings(int driverId, LocalDate startDate, LocalDate endDate) throws DBException;
}
