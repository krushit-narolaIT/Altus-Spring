package com.krushit.dao;

import com.krushit.common.Message;
import com.krushit.common.config.JPAConfig;
import com.krushit.common.enums.RideRequestStatus;
import com.krushit.common.enums.RideStatus;
import com.krushit.common.exception.DBException;
import com.krushit.dto.RideCancellationDetailsDTO;
import com.krushit.entity.Ride;
import com.krushit.entity.RideRequest;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Query;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

public class RideDAOImpl implements IRideDAO {
    private static final String GET_PENDING_RIDES_FOR_AVAILABLE_DRIVERS =
            "SELECT rr FROM RideRequest rr " +
                    "JOIN rr.vehicleService vs " +
                    "JOIN BrandModel bm ON bm.vehicleService.serviceId = vs.serviceId " +
                    "JOIN Vehicle v ON bm.brandModelId = v.brandModel.brandModelId " +
                    "JOIN Driver d ON v.driver.driverId = d.driverId " +
                    "WHERE d.isAvailable = TRUE " +
                    "AND rr.rideRequestStatus = com.krushit.common.enums.RideRequestStatus.PENDING " +
                    "AND d.driverId = :driverId";
    private static final String GET_DRIVER_RIDES_BY_DATE_RANGE =
            "SELECT r FROM Ride r WHERE r.driver.id = :driverId AND r.rideDate BETWEEN :startDate " +
                    "AND :endDate ORDER BY r.rideDate DESC";
    private static final String GET_RIDES_BY_DATE_RANGE =
            "SELECT r FROM Ride r WHERE r.rideDate BETWEEN :startDate AND :endDate " +
                    "ORDER BY r.rideDate DESC";
    private static final String GET_TOTAL_DRIVER_RIDES =
            "SELECT COUNT(r) FROM Ride r WHERE r.driver.id = :driverId AND r.rideDate " +
                    "BETWEEN :startDate AND :endDate";
    private static final String GET_TOTAL_RIDES =
            "SELECT COUNT(r) FROM Ride r WHERE r.rideDate " +
                    "BETWEEN :startDate AND :endDate";
    private static final String GET_TOTAL_DRIVER_EARNING =
            "SELECT SUM(r.driverEarning + r.cancellationDriverEarning - r.driverPenalty) " +
                    "FROM Ride r WHERE r.driver.id = :driverId AND r.rideDate BETWEEN :startDate AND :endDate";
    private static final String GET_TOTAL_EARNINGS =
            "SELECT SUM(r.systemEarning + r.cancellationSystemEarning) " +
                    "FROM Ride r WHERE r.rideDate BETWEEN :startDate AND :endDate";
    private static final String UPDATE_RIDE_STATUS =
            "UPDATE rides SET ride_status = ?, cancellation_charge = ?, cancellation_driver_earning = ?, " +
                    "cancellation_system_earning = ?, driver_penalty = ?, driver_earning =?, system_earning = ? WHERE ride_id = ?";
    private static final String GET_RIDE_BY_USER_ID =
            "SELECT r FROM Ride r WHERE r.customer.userId = :userId OR r.driver.userId = :userId " +
                    "ORDER BY r.rideDate DESC";
    private static final String GET_RIDE_STATUS =
            "SELECT r.rideStatus FROM Ride r WHERE r.rideId = :rideId";
    private static final String UPDATE_RIDE_REQUEST_STATUS = "UPDATE Ride_requests SET ride_request_status = ? " +
            "WHERE ride_request_id = ?";
    private static final String QUERY_CONFLICTING_RIDE =
            "SELECT r FROM Ride r " +
                    "WHERE r.driver.userId = :driverId " +
                    "AND r.rideDate = :rideDate " +
                    "AND r.pickUpTime >= :startTime AND r.pickUpTime <= :endTime " +
                    "AND r.rideStatus IN (:scheduledStatus, :ongoingStatus)";

    @Override
    public List<RideRequest> getAllMatchingRideRequests(int driverId) throws DBException {
        try (EntityManager em = JPAConfig.getEntityManagerFactory().createEntityManager()) {
            return em.createQuery(GET_PENDING_RIDES_FOR_AVAILABLE_DRIVERS, RideRequest.class)
                    .setParameter("driverId", driverId)
                    .getResultList();
        } catch (Exception e) {
            throw new DBException(Message.Ride.ERROR_WHILE_GETTING_ALL_RIDE_REQUEST_FOR_DRIVER, e);
        }
    }

    @Override
    public Optional<RideRequest> getRideRequest(int rideRequestId) throws DBException {
        try (EntityManager em = JPAConfig.getEntityManagerFactory().createEntityManager()) {
            RideRequest rideRequest = em.find(RideRequest.class, rideRequestId);
            return Optional.ofNullable(rideRequest);
        } catch (Exception e) {
            throw new DBException(Message.Ride.ERROR_WHILE_GETTING_RIDE_REQUEST_BY_ID, e);
        }
    }

    @Override
    public Optional<Ride> getConflictingRide(int driverId, LocalDate rideDate, LocalTime pickUpTime) throws DBException {
        try (EntityManager em = JPAConfig.getEntityManagerFactory().createEntityManager()) {
            LocalTime startTime = pickUpTime.minusMinutes(15);
            LocalTime endTime = pickUpTime.plusMinutes(30);
            List<Ride> rides = em.createQuery(QUERY_CONFLICTING_RIDE, Ride.class)
                    .setParameter("driverId", driverId)
                    .setParameter("rideDate", rideDate)
                    .setParameter("startTime", startTime)
                    .setParameter("endTime", endTime)
                    .setParameter("scheduledStatus", RideStatus.SCHEDULED)
                    .setParameter("ongoingStatus", RideStatus.ONGOING)
                    .getResultList();
            return rides.isEmpty() ? Optional.empty() : Optional.of(rides.get(0));
        } catch (Exception e) {
            throw new DBException(Message.Ride.ERROR_WHILE_GETTING_RIDE_REQUEST_BY_ID, e);
        }
    }

    @Override
    public void createRide(int rideRequestId, Ride ride) throws DBException {
        EntityManagerFactory emf = JPAConfig.getEntityManagerFactory();
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            em.persist(ride);
            RideRequest existing = em.find(RideRequest.class, rideRequestId);
            if (existing != null) {
                RideRequest updated = new RideRequest.RideRequestBuilder()
                        .setRideRequestId(existing.getRideRequestId())
                        .setRideRequestStatus(RideRequestStatus.ACCEPTED)
                        .setCustomer(existing.getCustomer())
                        .setVehicleService(existing.getVehicleService())
                        .setPickUpLocation(existing.getPickUpLocation())
                        .setDropOffLocation(existing.getDropOffLocation())
                        .setRideDate(existing.getRideDate())
                        .setPickUpTime(existing.getPickUpTime())
                        .build();
                em.merge(updated);
            }
            tx.commit();
        } catch (Exception e) {
            if (tx.isActive()) tx.rollback();
            throw new DBException(Message.Ride.ERROR_WHILE_CREATING_RIDE, e);
        } finally {
            em.close();
        }
    }

    @Override
    public Optional<Ride> getRide(int rideId) throws DBException {
        try (EntityManager em = JPAConfig.getEntityManagerFactory().createEntityManager()) {
            Ride ride = em.find(Ride.class, rideId);
            return Optional.ofNullable(ride);
        } catch (Exception e) {
            throw new DBException(Message.Ride.ERROR_WHILE_GETTING_RIDE, e);
        }
    }

    @Override
    public void updateRideCancellation(RideCancellationDetailsDTO cancellationDetails) throws DBException {
        EntityTransaction tx = null;
        try (EntityManager em = JPAConfig.getEntityManagerFactory().createEntityManager()) {
            tx = em.getTransaction();
            tx.begin();
            Ride existing = em.find(Ride.class, cancellationDetails.getRideId());
            if (existing != null) {
                Ride updated = new Ride.RideBuilder()
                        .setRideId(existing.getRideId())
                        .setRideStatus(RideStatus.CANCELLED)
                        .setCustomer(existing.getCustomer())
                        .setDriver(existing.getDriver())
                        .setPickLocation(existing.getPickLocation())
                        .setDropOffLocation(existing.getDropOffLocation())
                        .setRideDate(existing.getRideDate())
                        .setPickUpTime(existing.getPickUpTime())
                        .setDropOffTime(existing.getDropOffTime())
                        .setDisplayId(existing.getDisplayId())
                        .setPaymentMode(existing.getPaymentMode())
                        .setPaymentStatus(existing.getPaymentStatus())
                        .setTotalKm(existing.getTotalKm())
                        .setTotalCost(existing.getTotalCost())
                        .setCommissionPercentage(existing.getCommissionPercentage())
                        .setCancellationCharge(BigDecimal.valueOf(cancellationDetails.getCancellationCharge()))
                        .setCancellationDriverEarning(BigDecimal.valueOf(cancellationDetails.getDriverEarning()))
                        .setCancellationSystemEarning(BigDecimal.valueOf(cancellationDetails.getSystemEarning()))
                        .setDriverPenalty(BigDecimal.valueOf(cancellationDetails.getDriverPenalty()))
                        .setDriverEarning(BigDecimal.ZERO)
                        .setSystemEarning(
                                BigDecimal.valueOf(cancellationDetails.getDriverPenalty()).compareTo(BigDecimal.ZERO) != 0
                                        ? BigDecimal.valueOf(120)
                                        : BigDecimal.ZERO
                        )
                        .build();
                em.merge(updated);
            }
            tx.commit();
        } catch (Exception e) {
            if (tx != null && tx.isActive()) tx.rollback();
            throw new DBException(Message.Ride.ERROR_WHILE_RIDE_CANCELLATION, e);
        }
    }

    @Override
    public List<Ride> getAllRidesByUserId(int userId) throws DBException {
        try (EntityManager em = JPAConfig.getEntityManagerFactory().createEntityManager()) {
            return em.createQuery(GET_RIDE_BY_USER_ID, Ride.class)
                    .setParameter("userId", userId)
                    .getResultList();
        } catch (Exception e) {
            throw new DBException(Message.Ride.ERROR_WHILE_GETTING_ALL_RIDES, e);
        }
    }

    @Override
    public RideStatus getRideStatus(int rideId) throws DBException {
        try (EntityManager em = JPAConfig.getEntityManagerFactory().createEntityManager()) {
            return em.createQuery(GET_RIDE_STATUS, RideStatus.class)
                    .setParameter("rideId", rideId)
                    .getSingleResult();
        } catch (Exception e) {
            throw new DBException(Message.Ride.ERROR_WHILE_FETCHING_RIDE_STATUS, e);
        }
    }

    @Override
    public List<Ride> getRidesByDateRange(int driverId, LocalDate startDate, LocalDate endDate) throws DBException {
        try (EntityManager em = JPAConfig.getEntityManagerFactory().createEntityManager()) {
            String jpql = driverId == 0 ? GET_RIDES_BY_DATE_RANGE : GET_DRIVER_RIDES_BY_DATE_RANGE;
            Query query = em.createQuery(jpql);
            if (driverId != 0) {
                query.setParameter("driverId", driverId);
            }
            query.setParameter("startDate", startDate);
            query.setParameter("endDate", endDate);
            @SuppressWarnings("unchecked")
            List<Ride> rideList = query.getResultList();
            return rideList;
        } catch (Exception e) {
            throw new DBException(Message.Ride.ERROR_WHILE_FETCHING_RIDE_DETAILS_BY_RANGE, e);
        }
    }

    @Override
    public int getTotalRides(int driverId, LocalDate startDate, LocalDate endDate) throws DBException {
        try (EntityManager em = JPAConfig.getEntityManagerFactory().createEntityManager()) {
            String jpql = driverId == 0 ? GET_TOTAL_RIDES : GET_TOTAL_DRIVER_RIDES;
            Query query = em.createQuery(jpql);
            if (driverId != 0) {
                query.setParameter("driverId", driverId);
            }
            query.setParameter("startDate", startDate);
            query.setParameter("endDate", endDate);
            Long result = (Long) query.getSingleResult();
            return result != null ? result.intValue() : 0;
        } catch (Exception e) {
            throw new DBException(Message.Ride.ERROR_WHILE_FETCHING_TOTAL_RIDES, e);
        }
    }

    @Override
    public double getTotalEarnings(int driverId, LocalDate startDate, LocalDate endDate) throws DBException {
        try (EntityManager em = JPAConfig.getEntityManagerFactory().createEntityManager()) {
            String jpql = driverId == 0 ? GET_TOTAL_EARNINGS : GET_TOTAL_DRIVER_EARNING;
            Query query = em.createQuery(jpql);
            if (driverId != 0) {
                query.setParameter("driverId", driverId);
            }
            query.setParameter("startDate", startDate);
            query.setParameter("endDate", endDate);
            BigDecimal result = (BigDecimal) query.getSingleResult();
            return result != null ? result.doubleValue() : 0.0;
        } catch (Exception e) {
            throw new DBException(Message.Ride.ERROR_WHILE_CALCULATING_DRIVER_TOTAL_EARNING, e);
        }
    }
}