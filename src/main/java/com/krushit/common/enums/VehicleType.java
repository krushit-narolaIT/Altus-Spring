package com.krushit.common.enums;

public enum VehicleType {
    TWO_WHEELER,
    THREE_WHEELER,
    FOUR_WHEELER;

    public static boolean isValidVehicleType(VehicleType vehicleType) {
        for (VehicleType type : VehicleType.values()) {
            if (type.equals(vehicleType)) {
                return true;
            }
        }
        return false;
    }
}
