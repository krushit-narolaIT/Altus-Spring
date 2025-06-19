package com.krushit.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.krushit.entity.CommissionSlab;

import java.math.BigDecimal;

public interface CommissionSlabRepository extends JpaRepository<CommissionSlab, Long> {

    @Query("SELECT c.commissionPercentage FROM CommissionSlab c WHERE :distance BETWEEN c.fromKm AND c.toKm")
    BigDecimal findCommissionByDistance(double distance);
}
