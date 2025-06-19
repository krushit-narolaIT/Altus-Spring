package com.krushit.dto;

import java.util.List;

public class DateRangeIncomeResponseDTO {
    private int totalRides;
    private double totalEarning;
    private List<RideDTO> ridesDTO;

    public DateRangeIncomeResponseDTO(int totalRides, double totalEarning, List<RideDTO> rides) {
        this.totalRides = totalRides;
        this.totalEarning = totalEarning;
        this.ridesDTO = rides;
    }

    public int getTotalRides() {
        return totalRides;
    }

    public void setTotalRides(int totalRides) {
        this.totalRides = totalRides;
    }

    public double getTotalEarning() {
        return totalEarning;
    }

    public void setTotalEarning(double totalEarning) {
        this.totalEarning = totalEarning;
    }

    public List<RideDTO> getRidesDTO() {
        return ridesDTO;
    }

    public void setRidesDTO(List<RideDTO> ridesDTO) {
        this.ridesDTO = ridesDTO;
    }
}
