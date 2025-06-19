package com.krushit.dto;

public class BrandModelResponseDTO {
    private int brandModelId;
    private String brandName;
    private String model;
    private String vehicleService;

    public BrandModelResponseDTO(int brandModelId, String brandName, String models, String vehicleService) {
        this.brandModelId = brandModelId;
        this.brandName = brandName;
        this.model = models;
        this.vehicleService = vehicleService;
    }

    public int getBrandModelId() {
        return brandModelId;
    }

    public void setBrandModelId(int brandModelId) {
        this.brandModelId = brandModelId;
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

    public String getVehicleService() {
        return vehicleService;
    }

    public void setVehicleService(String vehicleService) {
        this.vehicleService = vehicleService;
    }
}
