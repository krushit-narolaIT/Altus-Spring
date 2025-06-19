package com.krushit.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import com.krushit.common.enums.RideRequestStatus;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonDeserialize(builder = RideRequest.RideRequestBuilder.class)
@Entity
@Table(name = "ride_requests")
public class RideRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ride_request_id")
    private int rideRequestId;

    @Enumerated(EnumType.STRING)
    @Column(name = "ride_request_status", nullable = false)
    private RideRequestStatus rideRequestStatus;

    @ManyToOne
    @JoinColumn(name = "pick_up_location_id", nullable = false)
    private Location pickUpLocation;

    @ManyToOne
    @JoinColumn(name = "drop_off_location_id", nullable = false)
    private Location dropOffLocation;

    @ManyToOne
    @JoinColumn(name = "vehicle_service_id", nullable = false)
    private VehicleService vehicleService;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User customer;

    @Column(name = "ride_date", nullable = false)
    private LocalDate rideDate;

    @Column(name = "pick_up_time", nullable = false)
    private LocalTime pickUpTime;

    @Column(name = "created_at", updatable = false)
    @CreationTimestamp
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    @UpdateTimestamp
    private LocalDateTime updatedAt;

    private RideRequest(RideRequestBuilder builder) {
        this.rideRequestId = builder.rideRequestId;
        this.rideRequestStatus = builder.rideRequestStatus;
        this.pickUpLocation = builder.pickUpLocation;
        this.dropOffLocation = builder.dropOffLocation;
        this.vehicleService = builder.vehicleService;
        this.customer = builder.user;
        this.rideDate = builder.rideDate;
        this.pickUpTime = builder.pickUpTime;
        this.createdAt = builder.createdAt;
        this.updatedAt = builder.updatedAt;
    }

    public RideRequest() {
    }

    @JsonPOJOBuilder(withPrefix = "set")
    public static class RideRequestBuilder {
        private int rideRequestId;
        private RideRequestStatus rideRequestStatus;
        private Location pickUpLocation;
        private Location dropOffLocation;
        private VehicleService vehicleService;
        private User user;
        private LocalDate rideDate;
        private LocalTime pickUpTime;
        private LocalDateTime createdAt;
        private LocalDateTime updatedAt;

        public RideRequestBuilder setRideRequestId(int rideRequestId) {
            this.rideRequestId = rideRequestId;
            return this;
        }

        public RideRequestBuilder setRideRequestStatus(RideRequestStatus rideRequestStatus) {
            this.rideRequestStatus = rideRequestStatus;
            return this;
        }

        public RideRequestBuilder setPickUpLocation(Location pickUpLocation) {
            this.pickUpLocation = pickUpLocation;
            return this;
        }

        public RideRequestBuilder setDropOffLocation(Location dropOffLocation) {
            this.dropOffLocation = dropOffLocation;
            return this;
        }

        public RideRequestBuilder setVehicleService(VehicleService vehicleService) {
            this.vehicleService = vehicleService;
            return this;
        }

        public RideRequestBuilder setCustomer(User user) {
            this.user = user;
            return this;
        }

        public RideRequestBuilder setRideDate(LocalDate rideDate) {
            this.rideDate = rideDate;
            return this;
        }

        public RideRequestBuilder setPickUpTime(LocalTime pickUpTime) {
            this.pickUpTime = pickUpTime;
            return this;
        }

        public RideRequestBuilder setCreatedAt(LocalDateTime createdAt) {
            this.createdAt = createdAt;
            return this;
        }

        public RideRequestBuilder setUpdatedAt(LocalDateTime updatedAt) {
            this.updatedAt = updatedAt;
            return this;
        }

        public RideRequest build() {
            return new RideRequest(this);
        }
    }

    public int getRideRequestId() {
        return rideRequestId;
    }

    public RideRequestStatus getRideRequestStatus() {
        return rideRequestStatus;
    }

    public Location getPickUpLocation() {
        return pickUpLocation;
    }

    public Location getDropOffLocation() {
        return dropOffLocation;
    }

    public VehicleService getVehicleService() {
        return vehicleService;
    }

    public User getCustomer() {
        return customer;
    }

    public LocalDate getRideDate() {
        return rideDate;
    }

    public LocalTime getPickUpTime() {
        return pickUpTime;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    @Override
    public String toString() {
        return "RideRequest{" +
                "rideRequestId=" + rideRequestId +
                ", rideRequestStatus=" + rideRequestStatus +
                ", pickUpLocation=" + pickUpLocation +
                ", dropOffLocation=" + dropOffLocation +
                ", vehicleService=" + vehicleService +
                ", user=" + customer +
                ", rideDate=" + rideDate +
                ", pickUpTime=" + pickUpTime +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}
