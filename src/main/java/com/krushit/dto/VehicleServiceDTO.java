package com.krushit.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import com.krushit.common.enums.VehicleType;

import java.math.BigDecimal;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonDeserialize(builder = VehicleServiceDTO.Builder.class)
public class VehicleServiceDTO {
    private final String serviceName;
    private final BigDecimal baseFare;
    private final BigDecimal perKmRate;
    private final VehicleType vehicleType;
    private final int maxPassengers;
    private final BigDecimal commissionPercentage;

    private VehicleServiceDTO(Builder builder) {
        this.serviceName = builder.serviceName;
        this.baseFare = builder.baseFare;
        this.perKmRate = builder.perKmRate;
        this.vehicleType = builder.vehicleType;
        this.maxPassengers = builder.maxPassengers;
        this.commissionPercentage = builder.commissionPercentage;
    }

    public String getServiceName() {
        return serviceName;
    }

    public BigDecimal getBaseFare() {
        return baseFare;
    }

    public BigDecimal getPerKmRate() {
        return perKmRate;
    }

    public VehicleType getVehicleType() {
        return vehicleType;
    }

    public int getMaxPassengers() {
        return maxPassengers;
    }

    public BigDecimal getCommissionPercentage() {
        return commissionPercentage;
    }

    @Override
    public String toString() {
        return "VehicleServiceDTO{" +
                "serviceName='" + serviceName + '\'' +
                ", baseFare=" + baseFare +
                ", perKmRate=" + perKmRate +
                ", vehicleType=" + vehicleType +
                ", maxPassengers=" + maxPassengers +
                ", commissionPercentage=" + commissionPercentage +
                '}';
    }

    @JsonPOJOBuilder(withPrefix = "set")
    public static class Builder {
        private String serviceName;
        private BigDecimal baseFare;
        private BigDecimal perKmRate;
        private VehicleType vehicleType;
        private int maxPassengers;
        private BigDecimal commissionPercentage;

        public Builder setServiceName(String serviceName) {
            this.serviceName = serviceName;
            return this;
        }

        public Builder setBaseFare(BigDecimal baseFare) {
            this.baseFare = baseFare;
            return this;
        }

        public Builder setPerKmRate(BigDecimal perKmRate) {
            this.perKmRate = perKmRate;
            return this;
        }

        public Builder setVehicleType(VehicleType vehicleType) {
            this.vehicleType = vehicleType;
            return this;
        }

        public Builder setMaxPassengers(int maxPassengers) {
            this.maxPassengers = maxPassengers;
            return this;
        }

        public Builder setCommissionPercentage(BigDecimal commissionPercentage) {
            this.commissionPercentage = commissionPercentage;
            return this;
        }

        public VehicleServiceDTO build() {
            return new VehicleServiceDTO(this);
        }
    }
}
