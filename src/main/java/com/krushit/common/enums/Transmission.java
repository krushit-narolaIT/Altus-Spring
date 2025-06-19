package com.krushit.common.enums;

public enum Transmission {
    MANUAL,
    AUTOMATIC;

    public static boolean isValidTransmission(Transmission transmissionType) {
        for (Transmission type : Transmission.values()) {
            if (type.equals(transmissionType)) {
                return true;
            }
        }
        return false;
    }
}
