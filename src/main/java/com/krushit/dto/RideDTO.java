package com.krushit.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import com.krushit.common.enums.PaymentMode;
import com.krushit.common.enums.PaymentStatus;
import com.krushit.common.enums.RideStatus;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonDeserialize(builder = RideDTO.RideDTOBuilder.class)
public class RideDTO {
    private final int rideId;
    private final RideStatus rideStatus;
    private final String pickLocationId;
    private final String dropOffLocationId;
    private final String customerName;
    private final String driverName;
    private final LocalDate rideDate;
    private final LocalTime pickUpTime;
    private final LocalTime dropOffTime;
    private final String displayId;
    private final double totalKm;
    private final BigDecimal totalCost;
    private final PaymentMode paymentMode;
    private final PaymentStatus paymentStatus;

    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    private final double commissionPercentage;

    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    private final BigDecimal driverEarning;

    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    private final BigDecimal systemEarning;

    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    private final BigDecimal cancellationCharge;

    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    private final BigDecimal cancellationDriverEarning;

    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    private final BigDecimal cancellationSystemEarning;

    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    private final BigDecimal driverPenalty;

    private RideDTO(RideDTOBuilder builder) {
        this.rideId = builder.rideId;
        this.rideStatus = builder.rideStatus;
        this.pickLocationId = builder.pickLocationId;
        this.dropOffLocationId = builder.dropOffLocationId;
        this.customerName = builder.customerName;
        this.driverName = builder.driverName;
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

    @JsonPOJOBuilder(withPrefix = "set")
    public static class RideDTOBuilder {
        private int rideId;
        private RideStatus rideStatus;
        private String pickLocationId;
        private String dropOffLocationId;
        private String customerName;
        private String driverName;
        private LocalDate rideDate;
        private LocalTime pickUpTime;
        private LocalTime dropOffTime;
        private String displayId;
        private double totalKm;
        private BigDecimal totalCost;
        private PaymentMode paymentMode;
        private PaymentStatus paymentStatus;
        private double commissionPercentage;
        private BigDecimal driverEarning;
        private BigDecimal systemEarning;
        private BigDecimal cancellationCharge;
        private BigDecimal cancellationDriverEarning;
        private BigDecimal cancellationSystemEarning;
        private BigDecimal driverPenalty;

        public RideDTOBuilder setRideId(int rideId) {
            this.rideId = rideId;
            return this;
        }

        public RideDTOBuilder setRideStatus(RideStatus rideStatus) {
            this.rideStatus = rideStatus;
            return this;
        }

        public RideDTOBuilder setPickLocationId(String pickLocationId) {
            this.pickLocationId = pickLocationId;
            return this;
        }

        public RideDTOBuilder setDropOffLocationId(String dropOffLocationId) {
            this.dropOffLocationId = dropOffLocationId;
            return this;
        }

        public RideDTOBuilder setCustomerName(String customerName) {
            this.customerName = customerName;
            return this;
        }

        public RideDTOBuilder setDriverName(String driverName) {
            this.driverName = driverName;
            return this;
        }

        public RideDTOBuilder setRideDate(LocalDate rideDate) {
            this.rideDate = rideDate;
            return this;
        }

        public RideDTOBuilder setPickUpTime(LocalTime pickUpTime) {
            this.pickUpTime = pickUpTime;
            return this;
        }

        public RideDTOBuilder setDropOffTime(LocalTime dropOffTime) {
            this.dropOffTime = dropOffTime;
            return this;
        }

        public RideDTOBuilder setDisplayId(String displayId) {
            this.displayId = displayId;
            return this;
        }

        public RideDTOBuilder setTotalKm(double totalKm) {
            this.totalKm = totalKm;
            return this;
        }

        public RideDTOBuilder setTotalCost(BigDecimal totalCost) {
            this.totalCost = totalCost;
            return this;
        }

        public RideDTOBuilder setPaymentMode(PaymentMode paymentMode) {
            this.paymentMode = paymentMode;
            return this;
        }

        public RideDTOBuilder setPaymentStatus(PaymentStatus paymentStatus) {
            this.paymentStatus = paymentStatus;
            return this;
        }

        public RideDTOBuilder setCommissionPercentage(double commissionPercentage) {
            this.commissionPercentage = commissionPercentage;
            return this;
        }

        public RideDTOBuilder setDriverEarning(BigDecimal driverEarning) {
            this.driverEarning = driverEarning;
            return this;
        }

        public RideDTOBuilder setSystemEarning(BigDecimal systemEarning) {
            this.systemEarning = systemEarning;
            return this;
        }

        public RideDTOBuilder setCancellationCharge(BigDecimal cancellationCharge) {
            this.cancellationCharge = cancellationCharge;
            return this;
        }

        public RideDTOBuilder setCancellationDriverEarning(BigDecimal cancellationDriverEarning) {
            this.cancellationDriverEarning = cancellationDriverEarning;
            return this;
        }

        public RideDTOBuilder setCancellationSystemEarning(BigDecimal cancellationSystemEarning) {
            this.cancellationSystemEarning = cancellationSystemEarning;
            return this;
        }

        public RideDTOBuilder setDriverPenalty(BigDecimal driverPenalty) {
            this.driverPenalty = driverPenalty;
            return this;
        }

        public RideDTO build() {
            return new RideDTO(this);
        }
    }

    public int getRideId() {
        return rideId;
    }

    public RideStatus getRideStatus() {
        return rideStatus;
    }

    public String getPickLocationId() {
        return pickLocationId;
    }

    public String getDropOffLocationId() {
        return dropOffLocationId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public String getDriverName() {
        return driverName;
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

    public double getCommissionPercentage() {
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
}
