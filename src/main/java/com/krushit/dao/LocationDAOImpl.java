package com.krushit.dao;

import com.krushit.common.Message;
import com.krushit.common.config.JPAConfig;
import com.krushit.common.exception.DBException;
import com.krushit.entity.Location;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.PersistenceContext;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public class LocationDAOImpl implements ILocationDAO {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void addLocation(String location) throws DBException {
        EntityTransaction tx = null;
        try {
            tx = entityManager.getTransaction();
            tx.begin();
            Location newLocation = new Location();
            newLocation.setName(location);
            entityManager.persist(newLocation);
            tx.commit();
        } catch (Exception e) {
            if (tx != null && tx.isActive()) {
                tx.rollback();
            }
            throw new DBException(Message.Location.ERROR_WHILE_ADDING_LOCATION, e);
        }
    }

    @Override
    public String getLocationName(int locationId) throws DBException {
        try (EntityManager em = JPAConfig.getEntityManagerFactory().createEntityManager()) {
            Location location = em.find(Location.class, locationId);
            if(location != null && location.getIsActive()){
                return location.getName();
            }
            return null;
        } catch (Exception e) {
            throw new DBException(Message.Location.ERROR_WHILE_GETTING_LOCATION_BY_NAME, e);
        }
    }

    @Override
    public List<Location> getAllLocations() throws DBException {
        try (EntityManager em = JPAConfig.getEntityManagerFactory().createEntityManager()) {
            String jpql = "SELECT l FROM Location l";
            return em.createQuery(jpql, Location.class).getResultList();
        } catch (Exception e) {
            throw new DBException(Message.Location.ERROR_WHILE_GETTING_ALL_LOCATION, e);
        }
    }

    @Override
    public boolean isLocationActive(int locationId) throws DBException {
        try (EntityManager em = JPAConfig.getEntityManagerFactory().createEntityManager()) {
            Location location = em.find(Location.class, locationId);
            if (location == null) {
                throw new DBException(Message.Location.LOCATION_NOT_FOUND);
            }
            return location.getIsActive();
        } catch (Exception e) {
            throw new DBException(Message.Location.ERROR_WHILE_CHECKING_LOCATION_IS_ACTIVE_OR_NOT, e);
        }
    }

    @Override
    public Optional<Location> getLocation(int locationId) throws DBException {
        try (EntityManager em = JPAConfig.getEntityManagerFactory().createEntityManager()) {
            Location location = em.find(Location.class, locationId);
            return Optional.of(location);
        } catch (Exception e) {
            throw new DBException(Message.Location.ERROR_WHILE_CHECKING_LOCATION_IS_ACTIVE_OR_NOT, e);
        }
    }

    @Override
    public void inactivateLocation(int locationId) throws DBException {
        EntityTransaction tx = null;
        try (EntityManager em = JPAConfig.getEntityManagerFactory().createEntityManager()) {
            tx = em.getTransaction();
            tx.begin();
            Location location = em.find(Location.class, locationId);
            if (location != null) {
                location.setIsActive(false);
                em.merge(location);
            }
            tx.commit();
        } catch (Exception e) {
            if (tx != null && tx.isActive()) {
                tx.rollback();
            }
            throw new DBException(Message.Location.ERROR_WHILE_DELETING_LOCATION, e);
        }
    }

    @Override
    public void activateLocation(int locationId) throws DBException {
        EntityTransaction tx = null;
        try (EntityManager em = JPAConfig.getEntityManagerFactory().createEntityManager()) {
            tx = em.getTransaction();
            tx.begin();
            Location location = em.find(Location.class, locationId);
            if (location != null) {
                location.setIsActive(true);
                em.merge(location);
            }
            tx.commit();
        } catch (Exception e) {
            if (tx != null && tx.isActive()) {
                tx.rollback();
            }
            throw new DBException(Message.Location.ERROR_WHILE_DELETING_LOCATION, e);
        }
    }


    @Override
    public BigDecimal getCommissionByDistance(double distance) throws DBException {
        try (EntityManager em = JPAConfig.getEntityManagerFactory().createEntityManager()) {
            String jpql = "SELECT c.commissionPercentage FROM CommissionSlab c WHERE :distance BETWEEN c.fromKm AND c.toKm";
            return em.createQuery(jpql, BigDecimal.class)
                    .setParameter("distance", distance)
                    .getSingleResult();
        } catch (Exception e) {
            throw new DBException(Message.Location.ERROR_WHILE_GET_COMMISSION_BY_DISTANCE, e);
        }
    }
}
