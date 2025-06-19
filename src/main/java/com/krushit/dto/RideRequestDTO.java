package com.krushit.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

import java.time.LocalDate;
import java.time.LocalTime;

@JsonDeserialize(builder = RideRequestDTO.RideRequestDTOBuilder.class)
public class RideRequestDTO {
    private final int rideRequestId;
    private final int pickUpLocationId;
    private final int dropOffLocationId;
    private final int vehicleServiceId;
    private final int userId;
    private final LocalDate rideDate;
    private final LocalTime pickUpTime;

    private RideRequestDTO(RideRequestDTOBuilder builder) {
        this.rideRequestId = builder.rideRequestId;
        this.pickUpLocationId = builder.pickUpLocationId;
        this.dropOffLocationId = builder.dropOffLocationId;
        this.vehicleServiceId = builder.vehicleServiceId;
        this.userId = builder.userId;
        this.rideDate = builder.rideDate;
        this.pickUpTime = builder.pickUpTime;
    }

    @JsonPOJOBuilder(withPrefix = "set")
    public static class RideRequestDTOBuilder {
        private int rideRequestId;
        private int pickUpLocationId;
        private int dropOffLocationId;
        private int vehicleServiceId;
        private int userId;
        private LocalDate rideDate;
        private LocalTime pickUpTime;

        public RideRequestDTOBuilder setRideRequestId(int rideRequestId) {
            this.rideRequestId = rideRequestId;
            return this;
        }

        public RideRequestDTOBuilder setPickUpLocationId(int pickUpLocationId) {
            this.pickUpLocationId = pickUpLocationId;
            return this;
        }

        public RideRequestDTOBuilder setDropOffLocationId(int dropOffLocationId) {
            this.dropOffLocationId = dropOffLocationId;
            return this;
        }

        public RideRequestDTOBuilder setVehicleServiceId(int vehicleServiceId) {
            this.vehicleServiceId = vehicleServiceId;
            return this;
        }

        public RideRequestDTOBuilder setUserId(int userId) {
            this.userId = userId;
            return this;
        }

        public RideRequestDTOBuilder setRideDate(LocalDate rideDate) {
            this.rideDate = rideDate;
            return this;
        }

        public RideRequestDTOBuilder setPickUpTime(LocalTime pickUpTime) {
            this.pickUpTime = pickUpTime;
            return this;
        }

        public RideRequestDTO build() {
            return new RideRequestDTO(this);
        }
    }

    public int getRideRequestId() {
        return rideRequestId;
    }

    public int getPickUpLocationId() {
        return pickUpLocationId;
    }

    public int getDropOffLocationId() {
        return dropOffLocationId;
    }

    public int getVehicleServiceId() {
        return vehicleServiceId;
    }

    public int getUserId() {
        return userId;
    }

    public LocalDate getRideDate() {
        return rideDate;
    }

    public LocalTime getPickUpTime() {
        return pickUpTime;
    }

    @Override
    public String toString() {
        return "RideRequestDTO{" +
                "pickUpLocationId=" + pickUpLocationId +
                ", dropOffLocationId=" + dropOffLocationId +
                ", vehicleServiceId=" + vehicleServiceId +
                ", userId=" + userId +
                ", rideDate=" + rideDate +
                ", pickUpTime=" + pickUpTime +
                '}';
    }
}
