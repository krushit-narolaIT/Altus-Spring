package com.krushit.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import com.krushit.common.enums.PaymentMode;
import com.krushit.common.enums.PaymentStatus;
import com.krushit.common.enums.RideStatus;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Entity
@Table(name = "rides")
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonDeserialize(builder = Ride.RideBuilder.class)
public class Ride {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ride_id")
    private int rideId;

    @Enumerated(EnumType.STRING)
    @Column(name = "ride_status", nullable = false)
    private RideStatus rideStatus;

    @ManyToOne
    @JoinColumn(name = "pick_location_id", nullable = false)
    private Location pickLocation;

    @ManyToOne
    @JoinColumn(name = "drop_off_location_id", nullable = false)
    private Location dropOffLocation;

    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private User customer;

    @ManyToOne
    @JoinColumn(name = "driver_id", nullable = false)
    private User driver;

    @Column(name = "ride_date", nullable = false)
    private LocalDate rideDate;

    @Column(name = "pick_up_time", nullable = false)
    private LocalTime pickUpTime;

    @Column(name = "drop_off_time")
    private LocalTime dropOffTime;

    @Column(name = "display_id", length = 10, nullable = false)
    private String displayId;

    @Column(name = "total_km", nullable = false)
    private double totalKm;

    @Column(name = "total_cost", nullable = false)
    private BigDecimal totalCost;

    @Column(name = "created_at", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP", updatable = false)
    @CreationTimestamp
    private LocalDateTime createdAt;

    @Column(name = "updated_at", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
    @UpdateTimestamp
    private LocalDateTime updatedAt;

    @Enumerated(EnumType.STRING)
    @Column(name = "payment_mode", nullable = false)
    private PaymentMode paymentMode;

    @Enumerated(EnumType.STRING)
    @Column(name = "payment_status", nullable = false)
    private PaymentStatus paymentStatus;

    @Column(name = "commission_percentage", nullable = false, columnDefinition = "DEFAULT 0.0")
    private BigDecimal commissionPercentage;

    @Column(name = "driver_earning", nullable = false, columnDefinition = "DEFAULT 0.0")
    private BigDecimal driverEarning;

    @Column(name = "system_earning", nullable = false, columnDefinition = "DEFAULT 0.0")
    private BigDecimal systemEarning;

    @Column(name = "cancellation_charge", nullable = false, columnDefinition = "DEFAULT 0.0")
    private BigDecimal cancellationCharge;

    @Column(name = "cancellation_driver_earning", nullable = false, columnDefinition = "DEFAULT 0.0")
    private BigDecimal cancellationDriverEarning;

    @Column(name = "cancellation_system_earning", nullable = false, columnDefinition = "DEFAULT 0.0")
    private BigDecimal cancellationSystemEarning;

    @Column(name = "driver_penalty", nullable = false, columnDefinition = "DEFAULT 0.0")
    private BigDecimal driverPenalty;

    private Ride(RideBuilder builder) {
        this.rideId = builder.rideId;
        this.rideStatus = builder.rideStatus;
        this.pickLocation = builder.pickLocation;
        this.dropOffLocation = builder.dropOffLocation;
        this.customer = builder.customer;
        this.driver = builder.driver;
        this.rideDate = builder.rideDate;
        this.pickUpTime = builder.pickUpTime;
        this.dropOffTime = builder.dropOffTime;
        this.displayId = builder.displayId;
        this.totalKm = builder.totalKm;
        this.totalCost = builder.totalCost;
        this.paymentMode = builder.paymentMode;
        this.paymentStatus = builder.paymentStatus;
        this.commissionPercentage = builder.commissionPercentage;
        this.driverEarning = builder.driverEarning;
        this.systemEarning = builder.systemEarning;
        this.cancellationCharge = builder.cancellationCharge;
        this.cancellationDriverEarning = builder.cancellationDriverEarning;
        this.cancellationSystemEarning = builder.cancellationSystemEarning;
        this.driverPenalty = builder.driverPenalty;
    }

    public Ride() {
    }

    public int getRideId() {
        return rideId;
    }

    public RideStatus getRideStatus() {
        return rideStatus;
    }

    public Location getPickLocation() {
        return pickLocation;
    }

    public Location getDropOffLocation() {
        return dropOffLocation;
    }

    public User getCustomer() {
        return customer;
    }

    public User getDriver() {
        return driver;
    }

    public LocalDate getRideDate() {
        return rideDate;
    }

    public LocalTime getPickUpTime() {
        return pickUpTime;
    }

    public LocalTime getDropOffTime() {
        return dropOffTime;
    }

    public String getDisplayId() {
        return displayId;
    }

    public double getTotalKm() {
        return totalKm;
    }

    public BigDecimal getTotalCost() {
        return totalCost;
    }

    public PaymentMode getPaymentMode() {
        return paymentMode;
    }

    public PaymentStatus getPaymentStatus() {
        return paymentStatus;
    }

    public BigDecimal getCommissionPercentage() {
        return commissionPercentage;
    }

    public BigDecimal getDriverEarning() {
        return driverEarning;
    }

    public BigDecimal getSystemEarning() {
        return systemEarning;
    }

    public BigDecimal getCancellationCharge() {
        return cancellationCharge;
    }

    public BigDecimal getCancellationDriverEarning() {
        return cancellationDriverEarning;
    }

    public BigDecimal getCancellationSystemEarning() {
        return cancellationSystemEarning;
    }

    public BigDecimal getDriverPenalty() {
        return driverPenalty;
    }

    @JsonPOJOBuilder(withPrefix = "set")
    public static class RideBuilder {
        private int rideId;
        private RideStatus rideStatus;
        private Location pickLocation;
        private Location dropOffLocation;
        private User customer;
        private User driver;
        private LocalDate rideDate;
        private LocalTime pickUpTime;
        private LocalTime dropOffTime;
        private String displayId;
        private double totalKm;
        private BigDecimal totalCost;
        private PaymentMode paymentMode;
        private PaymentStatus paymentStatus;
        private BigDecimal commissionPercentage;
        private BigDecimal driverEarning;
        private BigDecimal systemEarning;
        private BigDecimal cancellationCharge;
        private BigDecimal cancellationDriverEarning;
        private BigDecimal cancellationSystemEarning;
        private BigDecimal driverPenalty;

        public RideBuilder setRideId(int rideId) {
            this.rideId = rideId;
            return this;
        }

        public RideBuilder setRideStatus(RideStatus rideStatus) {
            this.rideStatus = rideStatus;
            return this;
        }

        public RideBuilder setPickLocation(Location pickLocation) {
            this.pickLocation = pickLocation;
            return this;
        }

        public RideBuilder setDropOffLocation(Location dropOffLocation) {
            this.dropOffLocation = dropOffLocation;
            return this;
        }

        public RideBuilder setCustomer(User customer) {
            this.customer = customer;
            return this;
        }

        public RideBuilder setDriver(User driver) {
            this.driver = driver;
            return this;
        }

        public RideBuilder setRideDate(LocalDate rideDate) {
            this.rideDate = rideDate;
            return this;
        }

        public RideBuilder setPickUpTime(LocalTime pickUpTime) {
            this.pickUpTime = pickUpTime;
            return this;
        }

        public RideBuilder setDropOffTime(LocalTime dropOffTime) {
            this.dropOffTime = dropOffTime;
            return this;
        }

        public RideBuilder setDisplayId(String displayId) {
            this.displayId = displayId;
            return this;
        }

        public RideBuilder setTotalKm(double totalKm) {
            this.totalKm = totalKm;
            return this;
        }

        public RideBuilder setTotalCost(BigDecimal totalCost) {
            this.totalCost = totalCost;
            return this;
        }

        public RideBuilder setPaymentMode(PaymentMode paymentMode) {
            this.paymentMode = paymentMode;
            return this;
        }

        public RideBuilder setPaymentStatus(PaymentStatus paymentStatus) {
            this.paymentStatus = paymentStatus;
            return this;
        }

        public RideBuilder setCommissionPercentage(BigDecimal commissionPercentage) {
            this.commissionPercentage = commissionPercentage;
            return this;
        }

        public RideBuilder setDriverEarning(BigDecimal driverEarning) {
            this.driverEarning = driverEarning;
            return this;
        }

        public RideBuilder setSystemEarning(BigDecimal systemEarning) {
            this.systemEarning = systemEarning;
            return this;
        }

        public RideBuilder setCancellationCharge(BigDecimal cancellationCharge) {
            this.cancellationCharge = cancellationCharge;
            return this;
        }

        public RideBuilder setCancellationDriverEarning(BigDecimal cancellationDriverEarning) {
            this.cancellationDriverEarning = cancellationDriverEarning;
            return this;
        }

        public RideBuilder setCancellationSystemEarning(BigDecimal cancellationSystemEarning) {
            this.cancellationSystemEarning = cancellationSystemEarning;
            return this;
        }

        public RideBuilder setDriverPenalty(BigDecimal driverPenalty) {
            this.driverPenalty = driverPenalty;
            return this;
        }

        public Ride build() {
            return new Ride(this);
        }
    }
}
