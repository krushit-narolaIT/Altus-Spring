package com.krushit.entity;

import com.krushit.common.enums.FuelType;
import com.krushit.common.enums.Transmission;
import jakarta.persistence.*;

@Entity
@Table(name = "vehicles")
public class Vehicle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "vehicle_id")
    private int vehicleId;

    @ManyToOne
    @JoinColumn(name = "driver_id")
    private Driver driver;

    @ManyToOne
    @JoinColumn(name = "brand_model_id")
    private BrandModel brandModel;

    @Column(name = "registration_number")
    private String registrationNumber;

    @Column(name = "year")
    private int year;

    @Enumerated(EnumType.STRING)
    @Column(name = "fuel_type")
    private FuelType fuelType;

    @Enumerated(EnumType.STRING)
    @Column(name = "transmission")
    private Transmission transmission;

    @Column(name = "ground_clearance")
    private double groundClearance;

    @Column(name = "wheel_base")
    private double wheelBase;

    public Vehicle() {}

    public int getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(int vehicleId) {
        this.vehicleId = vehicleId;
    }

    public Driver getDriver() {
        return driver;
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
    }

    public BrandModel getBrandModel() {
        return brandModel;
    }

    public void setBrandModel(BrandModel brandModel) {
        this.brandModel = brandModel;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public FuelType getFuelType() {
        return fuelType;
    }

    public void setFuelType(FuelType fuelType) {
        this.fuelType = fuelType;
    }

    public Transmission getTransmission() {
        return transmission;
    }

    public void setTransmission(Transmission transmission) {
        this.transmission = transmission;
    }

    public double getGroundClearance() {
        return groundClearance;
    }

    public void setGroundClearance(double groundClearance) {
        this.groundClearance = groundClearance;
    }

    public double getWheelBase() {
        return wheelBase;
    }

    public void setWheelBase(double wheelBase) {
        this.wheelBase = wheelBase;
    }

    @Override
    public String toString() {
        return "Vehicle{" +
                "vehicleId=" + vehicleId +
                ", driver=" + driver +
                ", brandModel=" + brandModel +
                ", registrationNumber='" + registrationNumber + '\'' +
                ", year=" + year +
                ", fuelType='" + fuelType + '\'' +
                ", transmission='" + transmission + '\'' +
                ", groundClearance=" + groundClearance +
                ", wheelBase=" + wheelBase +
                '}';
    }
}
