package com.krushit.dto;


import java.util.List;

public class BrandModelsResponseDTO {
    private String brandName;
    private List<String> models;
    private List<String> vehicleService;

    public BrandModelsResponseDTO(String brandName, List<String> models, List<String> vehicleService) {
        this.brandName = brandName;
        this.models = models;
        this.vehicleService = vehicleService;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public List<String> getModels() {
        return models;
    }

    public void setModels(List<String> models) {
        this.models = models;
    }

    public List<String> getVehicleService() {
        return vehicleService;
    }

    public void setVehicleService(List<String> vehicleService) {
        this.vehicleService = vehicleService;
    }
}
