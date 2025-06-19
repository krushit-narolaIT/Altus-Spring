package com.krushit.dto;

public class DistanceCalculatorDTO {
    private int from;
    private int to;
    private double price;

    public DistanceCalculatorDTO() {
    }

    public DistanceCalculatorDTO(int from, int to, double price) {
        this.from = from;
        this.to = to;
        this.price = price;
    }

    public int getFrom() {
        return from;
    }

    public void setFrom(int from) {
        this.from = from;
    }

    public int getTo() {
        return to;
    }

    public void setTo(int to) {
        this.to = to;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
