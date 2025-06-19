package com.krushit.entity;

import com.krushit.common.enums.VehicleType;
import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "vehicle_service")
public class VehicleService {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "service_id")
    private int serviceId;

    @Column(name = "service_name", nullable = false)
    private String serviceName;

    @Column(name = "base_fare")
    private BigDecimal baseFare;

    @Column(name = "per_km_rate")
    private BigDecimal perKmRate;

    @Enumerated(EnumType.STRING)
    @Column(name = "vehicle_type")
    private VehicleType vehicleType;

    @Column(name = "max_passengers")
    private int maxPassengers;

    @Column(name = "commission_percentage")
    private BigDecimal commissionPercentage;

    public VehicleService() {}

    public VehicleService(int serviceId, String serviceName, BigDecimal baseFare, BigDecimal perKmRate,
                          VehicleType vehicleType, int maxPassengers, BigDecimal commissionPercentage) {
        this.serviceId = serviceId;
        this.serviceName = serviceName;
        this.baseFare = baseFare;
        this.perKmRate = perKmRate;
        this.vehicleType = vehicleType;
        this.maxPassengers = maxPassengers;
        this.commissionPercentage = commissionPercentage;
    }

    public VehicleService(int serviceId, String serviceName, BigDecimal baseFare, BigDecimal perKmRate,
                          VehicleType vehicleType, int maxPassengers) {
        this.serviceId = serviceId;
        this.serviceName = serviceName;
        this.baseFare = baseFare;
        this.perKmRate = perKmRate;
        this.vehicleType = vehicleType;
        this.maxPassengers = maxPassengers;
    }

    public int getServiceId() {
        return serviceId;
    }

    public void setServiceId(int serviceId) {
        this.serviceId = serviceId;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public BigDecimal getBaseFare() {
        return baseFare;
    }

    public void setBaseFare(BigDecimal baseFare) {
        this.baseFare = baseFare;
    }

    public BigDecimal getPerKmRate() {
        return perKmRate;
    }

    public void setPerKmRate(BigDecimal perKmRate) {
        this.perKmRate = perKmRate;
    }

    public VehicleType getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(VehicleType vehicleType) {
        this.vehicleType = vehicleType;
    }

    public int getMaxPassengers() {
        return maxPassengers;
    }

    public void setMaxPassengers(int maxPassengers) {
        this.maxPassengers = maxPassengers;
    }

    public BigDecimal getCommissionPercentage() {
        return commissionPercentage;
    }

    public void setCommissionPercentage(BigDecimal commissionPercentage) {
        this.commissionPercentage = commissionPercentage;
    }

    @Override
    public String toString() {
        return "VehicleService [serviceId=" + serviceId + ", serviceName=" + serviceName +
                ", baseFare=" + baseFare + ", perKmRate=" + perKmRate + ", vehicleType=" + vehicleType +
                ", maxPassengers=" + maxPassengers + ", commissionPercentage=" + commissionPercentage + "]";
    }
}
