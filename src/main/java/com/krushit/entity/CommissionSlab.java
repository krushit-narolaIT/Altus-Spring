package com.krushit.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "commission_slabs")
public class CommissionSlab {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "commission_id")
    private int commissionId;

    @Column(name = "from_km", nullable = false)
    private double fromKm;

    @Column(name = "to_km", nullable = false)
    private double toKm;

    @Column(name = "commission_percentage", nullable = false)
    private BigDecimal commissionPercentage;

    public int getCommissionId() {
        return commissionId;
    }

    public void setCommissionId(int commissionId) {
        this.commissionId = commissionId;
    }

    public Double getFromKm() {
        return fromKm;
    }

    public void setFromKm(Double fromKm) {
        this.fromKm = fromKm;
    }

    public Double getToKm() {
        return toKm;
    }

    public void setToKm(Double toKm) {
        this.toKm = toKm;
    }

    public BigDecimal getCommissionPercentage() {
        return commissionPercentage;
    }

    public void setCommissionPercentage(BigDecimal commissionPercentage) {
        this.commissionPercentage = commissionPercentage;
    }
}
