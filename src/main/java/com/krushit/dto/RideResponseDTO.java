package com.krushit.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

import java.time.LocalDate;
import java.time.LocalTime;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonDeserialize(builder = RideResponseDTO.RideResponseDTOBuilder.class)
public class RideResponseDTO {
    private final int rideRequestId;
    private final String pickUpLocation;
    private final String dropOffLocation;
    private final String vehicleServiceId;
    private final String userDisplayId;
    private final String userFullName;
    private final LocalDate rideDate;
    private final LocalTime pickUpTime;

    private RideResponseDTO(RideResponseDTOBuilder builder) {
        this.rideRequestId = builder.rideRequestId;
        this.pickUpLocation = builder.pickUpLocation;
        this.dropOffLocation = builder.dropOffLocation;
        this.vehicleServiceId = builder.vehicleServiceId;
        this.userFullName = builder.userFullName;
        this.userDisplayId = builder.userDisplayId;
        this.rideDate = builder.rideDate;
        this.pickUpTime = builder.pickUpTime;
    }

    @JsonPOJOBuilder(withPrefix = "set")
    public static class RideResponseDTOBuilder {
        private int rideRequestId;
        private String pickUpLocation;
        private String dropOffLocation;
        private String vehicleServiceId;
        private String userDisplayId;
        private String userFullName;
        private LocalDate rideDate;
        private LocalTime pickUpTime;

        public RideResponseDTOBuilder setRideRequestId(int rideRequestId) {
            this.rideRequestId = rideRequestId;
            return this;
        }

        public RideResponseDTOBuilder setPickUpLocation(String pickUpLocation) {
            this.pickUpLocation = pickUpLocation;
            return this;
        }

        public RideResponseDTOBuilder setDropOffLocation(String dropOffLocation) {
            this.dropOffLocation = dropOffLocation;
            return this;
        }

        public RideResponseDTOBuilder setVehicleServiceId(String vehicleServiceId) {
            this.vehicleServiceId = vehicleServiceId;
            return this;
        }

        public RideResponseDTOBuilder setUserDisplayId(String userDisplayId) {
            this.userDisplayId = userDisplayId;
            return this;
        }

        public RideResponseDTOBuilder setUserFullName(String userFullName) {
            this.userFullName = userFullName;
            return this;
        }

        public RideResponseDTOBuilder setRideDate(LocalDate rideDate) {
            this.rideDate = rideDate;
            return this;
        }

        public RideResponseDTOBuilder setPickUpTime(LocalTime pickUpTime) {
            this.pickUpTime = pickUpTime;
            return this;
        }

        public RideResponseDTO build() {
            return new RideResponseDTO(this);
        }
    }

    public String getUserFullName() {
        return userFullName;
    }

    public int getRideRequestId() {
        return rideRequestId;
    }

    public String getPickUpLocation() {
        return pickUpLocation;
    }

    public String getDropOffLocation() {
        return dropOffLocation;
    }

    public String getVehicleServiceId() {
        return vehicleServiceId;
    }

    public String getUserDisplayId() {
        return userDisplayId;
    }

    public LocalDate getRideDate() {
        return rideDate;
    }

    public LocalTime getPickUpTime() {
        return pickUpTime;
    }

    @Override
    public String toString() {
        return "RideResponseDTO{" +
                "rideRequestId=" + rideRequestId +
                ", pickUpLocation='" + pickUpLocation + '\'' +
                ", dropOffLocation='" + dropOffLocation + '\'' +
                ", vehicleServiceId='" + vehicleServiceId + '\'' +
                ", userDisplayId='" + userDisplayId + '\'' +
                ", rideDate=" + rideDate +
                ", pickUpTime=" + pickUpTime +
                '}';
    }
}