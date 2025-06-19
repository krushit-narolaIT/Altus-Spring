package com.krushit.dto;

public class BrandModelRequestDTO {
    private int serviceId;
    private String brandName;
    private String model;
    private int minYear;

    public BrandModelRequestDTO(int serviceId, String brandName, String models, int vehicleService) {
        this.serviceId = serviceId;
        this.brandName = brandName;
        this.model = models;
        this.minYear = vehicleService;
    }

    public int getServiceId() {
        return serviceId;
    }

    public void setServiceId(int serviceId) {
        this.serviceId = serviceId;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getVehicleService() {
        return minYear;
    }

    public void setVehicleService(int vehicleService) {
        this.minYear = vehicleService;
    }

    public int getMinYear() {
        return minYear;
    }

    public void setMinYear(int minYear) {
        this.minYear = minYear;
    }
}
