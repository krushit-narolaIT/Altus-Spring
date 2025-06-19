package com.krushit.dao;

import com.krushit.common.exception.DBException;
import com.krushit.entity.Location;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface ILocationDAO {
    void addLocation(String location) throws DBException;
    String getLocationName(int locationId) throws DBException;
    List<Location> getAllLocations() throws DBException;
    boolean isLocationActive(int locationId) throws DBException;
    Optional<Location> getLocation(int locationId) throws DBException;
    void inactivateLocation(int locationId) throws DBException;
    void activateLocation(int locationId) throws DBException;
    BigDecimal getCommissionByDistance(double distance) throws DBException;
}
