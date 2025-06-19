package com.krushit.dto;

import com.krushit.common.enums.RideStatus;

public class RideCancellationDetailsDTO {
    private final int rideId;
    private final RideStatus rideStatus;
    private final double cancellationCharge;
    private final double driverEarning;
    private final double systemEarning;
    private final double driverPenalty;

    private RideCancellationDetailsDTO(RideCancellationDetailsBuilder builder) {
        this.rideId = builder.rideId;
        this.rideStatus = builder.rideStatus;
        this.cancellationCharge = builder.cancellationCharge;
        this.driverEarning = builder.driverEarning;
        this.systemEarning = builder.systemEarning;
        this.driverPenalty = builder.driverPenalty;
    }

    public int getRideId() {
        return rideId;
    }

    public RideStatus getRideStatus() {
        return rideStatus;
    }

    public double getCancellationCharge() {
        return cancellationCharge;
    }

    public double getDriverEarning() {
        return driverEarning;
    }

    public double getSystemEarning() {
        return systemEarning;
    }

    public double getDriverPenalty() {
        return driverPenalty;
    }

    public static class RideCancellationDetailsBuilder {
        private int rideId;
        private RideStatus rideStatus;
        private double cancellationCharge;
        private double driverEarning;
        private double systemEarning;
        private double driverPenalty;

        public RideCancellationDetailsBuilder setRideId(int rideId) {
            this.rideId = rideId;
            return this;
        }

        public RideCancellationDetailsBuilder setRideStatus(RideStatus rideStatus) {
            this.rideStatus = rideStatus;
            return this;
        }

        public RideCancellationDetailsBuilder setCancellationCharge(double cancellationCharge) {
            this.cancellationCharge = cancellationCharge;
            return this;
        }

        public RideCancellationDetailsBuilder setDriverEarning(double driverEarning) {
            this.driverEarning = driverEarning;
            return this;
        }

        public RideCancellationDetailsBuilder setSystemEarning(double systemEarning) {
            this.systemEarning = systemEarning;
            return this;
        }

        public RideCancellationDetailsBuilder setDriverPenalty(double driverPenalty) {
            this.driverPenalty = driverPenalty;
            return this;
        }

        public RideCancellationDetailsDTO build() {
            return new RideCancellationDetailsDTO(this);
        }
    }

    @Override
    public String toString() {
        return "RideCancellationDetailsDTO{" +
                "rideId=" + rideId +
                ", rideStatus=" + rideStatus +
                ", cancellationCharge=" + cancellationCharge +
                ", driverEarning=" + driverEarning +
                ", systemEarning=" + systemEarning +
                ", driverPenalty=" + driverPenalty +
                '}';
    }
}
