package com.krushit.common.enums;

public enum FuelType {
    PETROL,
    DIESEL,
    CNG,
    ELECTRIC,
    HYBRID;

    public static boolean isValidFuelType(FuelType fuelType) {
        for (FuelType type : FuelType.values()) {
            if (type.equals(fuelType)) {
                return true;
            }
        }
        return false;
    }
}
