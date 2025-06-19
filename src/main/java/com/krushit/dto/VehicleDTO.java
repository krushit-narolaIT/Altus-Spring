package com.krushit.dto;

public class VehicleDTO {
    private int vehicleId;
    private String serviceName;
    private double baseFare;
    private double perKmRate;
    private double commissionPercentage;

    public VehicleDTO() {
    }

    public VehicleDTO(int vehicleId, String serviceName, double baseFare, double perKmRate, double commissionPercentage) {
        this.vehicleId = vehicleId;
        this.serviceName = serviceName;
        this.baseFare = baseFare;
        this.perKmRate = perKmRate;
        this.commissionPercentage = commissionPercentage;
    }

    public int getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(int vehicleId) {
        this.vehicleId = vehicleId;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public double getBaseFare() {
        return baseFare;
    }

    public void setBaseFare(double baseFare) {
        this.baseFare = baseFare;
    }

    public double getPerKmRate() {
        return perKmRate;
    }

    public void setPerKmRate(double perKmRate) {
        this.perKmRate = perKmRate;
    }

    public double getCommissionPercentage() {
        return commissionPercentage;
    }

    public void setCommissionPercentage(double commissionPercentage) {
        this.commissionPercentage = commissionPercentage;
    }
}
