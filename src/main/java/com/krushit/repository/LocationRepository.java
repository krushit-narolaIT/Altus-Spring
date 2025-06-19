package com.krushit.repository;

import com.krushit.entity.Location;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.List;

@Repository
public interface LocationRepository extends JpaRepository<Location, Integer> {
    Optional<Location> findByIdAndIsActiveTrue(Integer id);

    @Query("SELECT l.name FROM Location l WHERE l.id = :locationId AND l.isActive = true")
    Optional<String> getLocationNameIfActive(Integer locationId);

    @Query("SELECT " +
            "CASE  " +
            "   WHEN COUNT(l) > 0 THEN true " +
            "   ELSE false " +
            "END " +
            "FROM Location l WHERE l.id = :locationId AND l.isActive = true")
    boolean existsByIdAndIsActiveTrue(Integer locationId);

    List<Location> findAllByOrderByName();

    Page<Location> findByIsActive(Boolean active, Pageable pageable);
}
