package com.krushit.dao;

import com.krushit.common.Message;
import com.krushit.common.config.JPAConfig;
import com.krushit.common.enums.DocumentVerificationStatus;
import com.krushit.common.exception.DBException;
import com.krushit.dto.PendingDriverDTO;
import com.krushit.entity.Driver;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class DriverDAOImpl implements IDriverDAO {
    private static final String IS_DOCUMENT_UNDER_REVIEW =
            "SELECT d.verificationStatus FROM Driver d WHERE d.driverId = :driverId";
    private static final String UPDATE_DRIVER_DETAILS =
            "UPDATE Driver d SET d.licenceNumber = :licenceNumber, d.licencePhoto = :licencePhoto," +
                    " d.verificationStatus = :verification_status WHERE d.user.userId = :driverId";
    private static final String GET_PENDING_VERIFICATION_DRIVERS =
            "SELECT d.driverId, d.user.userId, d.licenceNumber, d.licencePhoto, " +
                    "d.isDocumentVerified, d.comment, " +
                    "u.emailId, u.firstName, u.lastName, u.displayId " +
                    "FROM Driver d " +
                    "JOIN User u ON d.user.userId = u.userId " +
                    "WHERE d.isDocumentVerified = FALSE";
    private static final String UPDATE_DRIVER_VERIFICATION_STATUS =
            "UPDATE Driver SET verificationStatus = :verificationStatus, comment = :comment, " +
                    "isDocumentVerified = :isDocumentVerified, isAvailable = :isAvailable" +
                    " WHERE driverId = :driverId";
    private static final String CHECK_DRIVER_DOCUMENTS =
            "SELECT d.verificationStatus FROM Driver d WHERE d.driverId = :driverId";
    private static final String GET_ALL_DRIVERS =
            "SELECT d FROM Driver d JOIN FETCH d.user";
    private static final String GET_DRIVER_FROM_USERID =
            "SELECT d FROM Driver d WHERE d.user.userId = :userId";
    private static final String IS_DOCUMENT_VERIFIED =
            "SELECT isDocumentVerified FROM Driver WHERE driverId = :driverId";
    private static final String IS_LICENCE_EXIST =
            "SELECT 1 FROM Driver WHERE licenceNumber = :licenceNumber";
    private static final String UPDATE_DRIVER_AVAILABILITY =
            "UPDATE Driver d SET d.isAvailable = true WHERE d.driverId = :driverId";

    @Override
    public void insertDriverDetails(Driver driver) throws DBException {
        EntityTransaction tx = null;
        try (EntityManager em = JPAConfig.getEntityManagerFactory().createEntityManager()) {
            tx = em.getTransaction();
            tx.begin();
            em.createQuery(UPDATE_DRIVER_DETAILS)
                    .setParameter("driverId", driver.getUser().getUserId())
                    .setParameter("licenceNumber", driver.getLicenceNumber())
                    .setParameter("licencePhoto", driver.getLicencePhoto())
                    .setParameter("verification_status", DocumentVerificationStatus.PENDING)
                    .executeUpdate();
            tx.commit();
        } catch (Exception e) {
            if (tx != null && tx.isActive()) {
                tx.rollback();
            }
            throw new DBException(Message.Driver.ERROR_WHILE_INSERT_DRIVER_DETAILS, e);
        }
    }

    /*@Override
    public void insertDriverDetails(Driver driver) throws DBException {
        EntityManager em = null;
        EntityTransaction tx = null;
        try {
            em = JPAConfig.getEntityManagerFactory().createEntityManager();
            tx = em.getTransaction();
            tx.begin();
            em.createQuery(UPDATE_DRIVER_DETAILS)
                    .setParameter("licenceNumber", driver.getLicenceNumber())
                    .setParameter("licencePhoto", driver.getLicencePhoto())
                    .setParameter("driverId", driver.getUser().getUserId())
                    .setParameter("verification_status", DocumentVerificationStatus.PENDING)
                    .executeUpdate();
            tx.commit();
        } catch (Exception e) {
            if (tx != null && tx.isActive()) {
                tx.rollback();
            }
            throw new DBException(Message.Driver.ERROR_WHILE_INSERT_DRIVER_DETAILS, e);
        } finally {
            if (em != null && em.isOpen()) {
                em.close();
            }
        }
    }*/

    @Override
    public List<PendingDriverDTO> getDriversWithPendingVerification() throws DBException {
        List<PendingDriverDTO> pendingDrivers = new ArrayList<>();
        EntityTransaction tx = null;
        try (EntityManager em = JPAConfig.getEntityManagerFactory().createEntityManager()) {
            tx = em.getTransaction();
            tx.begin();
            @SuppressWarnings("unchecked")
            List<Object[]> rows = em.createQuery(GET_PENDING_VERIFICATION_DRIVERS).getResultList();
            for (Object[] row : rows) {
                PendingDriverDTO dto = new PendingDriverDTO.PendingDriverDTOBuilder()
                        .setDriverId((Integer) row[0])
                        .setUserId((Integer) row[1])
                        .setLicenceNumber((String) row[2])
                        .setLicencePhoto((String) row[3])
                        .setDocumentVerified((Boolean) row[4])
                        .setComment((String) row[5])
                        .setEmailId((String) row[6])
                        .setFirstName((String) row[7])
                        .setLastName((String) row[8])
                        .setDisplayId((String) row[9])
                        .build();
                pendingDrivers.add(dto);
            }
            tx.commit();
        } catch (Exception e) {
            if (tx != null && tx.isActive()) {
                tx.rollback();
            }
            throw new DBException(Message.Driver.ERROR_WHILE_GETTING_PENDING_VERIFICATION_DRIVER, e);
        }
        return pendingDrivers;
    }

    @Override
    public boolean isDriverExist(int driverId) throws DBException {
        try (EntityManager em = JPAConfig.getEntityManagerFactory().createEntityManager()) {
            if (em.getReference(Driver.class, driverId) != null) {
                return true;
            }
        } catch (Exception e) {
            throw new DBException(Message.Driver.ERROR_WHILE_INSERT_DRIVER_DETAILS, e);
        }
        return false;
    }

    @Override
    public void updateDriveVerificationDetail(int driverId, boolean isVerified, String rejectionMessage) throws DBException {
        EntityTransaction tx = null;
        try (EntityManager em = JPAConfig.getEntityManagerFactory().createEntityManager()) {
            tx = em.getTransaction();
            tx.begin();
            em.createQuery(UPDATE_DRIVER_VERIFICATION_STATUS)
                    .setParameter("verificationStatus", isVerified ? DocumentVerificationStatus.VERIFIED : DocumentVerificationStatus.REJECTED)
                    .setParameter("comment", rejectionMessage)
                    .setParameter("isDocumentVerified", isVerified)
                    .setParameter("isAvailable", isVerified)
                    .setParameter("driverId", driverId)
                    .executeUpdate();
            tx.commit();
        } catch (Exception e) {
            if (tx != null && tx.isActive()) {
                tx.rollback();
            }
            throw new DBException(Message.Driver.ERROR_WHILE_INSERT_DRIVER_DETAILS, e);
        }
    }

    @Override
    public List<Driver> getAllDrivers() throws DBException {
        try (EntityManager em = JPAConfig.getEntityManagerFactory().createEntityManager()) {
            return em.createQuery(GET_ALL_DRIVERS, Driver.class).getResultList();
        } catch (Exception e) {
            throw new DBException(Message.Driver.ERROR_WHILE_FETCHING_ALL_DRIVERS, e);
        }
    }

    @Override
    public  Optional<Driver> getDriver(int userId) throws DBException {
        try (EntityManager em = JPAConfig.getEntityManagerFactory().createEntityManager()) {
            List<Driver> drivers = em.createQuery(
                            GET_DRIVER_FROM_USERID, Driver.class)
                    .setParameter("userId", userId)
                    .getResultList();
            return drivers.isEmpty() ? Optional.empty() : Optional.of(drivers.get(0));
        } catch (Exception e) {
            throw new DBException(Message.Driver.ERROR_FOR_GETTING_DRIVER_ID_FROM_USER_ID, e);
        }
    }

    @Override
    public boolean isDocumentVerified(int driverId) throws DBException {
        try (EntityManager em = JPAConfig.getEntityManagerFactory().createEntityManager()) {
            Boolean verified = em.createQuery(
                            IS_DOCUMENT_VERIFIED, Boolean.class)
                    .setParameter("driverId", driverId)
                    .getSingleResult();
            return Boolean.TRUE.equals(verified);
        } catch (Exception e) {
            throw new DBException(Message.Driver.ERROR_FOR_CHECKING_DRIVER_DOCUMENT_VERIFIED, e);
        }
    }

    @Override
    public boolean isDocumentExist(int driverId) throws DBException {
        try (EntityManager em = JPAConfig.getEntityManagerFactory().createEntityManager()) {
            List<DocumentVerificationStatus> results = em.createQuery(
                            CHECK_DRIVER_DOCUMENTS, DocumentVerificationStatus.class)
                    .setParameter("driverId", driverId)
                    .getResultList();
            if (results.isEmpty()) {
                return false;
            }
            DocumentVerificationStatus status = results.get(0);
            return !status.equals(DocumentVerificationStatus.INCOMPLETE);
        } catch (Exception e) {
            throw new DBException(Message.Driver.ERROR_FOR_CHECKING_DRIVER_DOCUMENT_UPLOADED, e);
        }
    }

    @Override
    public boolean isLicenseNumberExist(String licenseNumber) throws DBException {
        try (EntityManager em = JPAConfig.getEntityManagerFactory().createEntityManager()) {
            List<Integer> result = em.createNamedQuery("Driver.findByLicenceNumber", Integer.class)
                    .setParameter("licenceNumber", licenseNumber)
                    .getResultList();
            return !result.isEmpty();
        } catch (Exception e) {
            throw new DBException(Message.Driver.ERROR_WHILE_CHECKING_LICENCE_NUMBER, e);
        }
    }

    @Override
    public DocumentVerificationStatus isDocumentUnderReview(int driverId) throws DBException {
        try (EntityManager em = JPAConfig.getEntityManagerFactory().createEntityManager()) {
            List<DocumentVerificationStatus> resultList = em.createQuery(
                            IS_DOCUMENT_UNDER_REVIEW, DocumentVerificationStatus.class)
                    .setParameter("driverId", driverId)
                    .getResultList();
            return resultList.isEmpty() ? null : resultList.get(0);
        } catch (Exception e) {
            throw new DBException(Message.Driver.ERROR_WHILE_CHECKING_IS_DOCUMENT_UNDER_REVIEW, e);
        }
    }

    @Override
    public void updateDriverAvailability(int driverId) throws DBException {
        EntityTransaction tx = null;
        try (EntityManager em = JPAConfig.getEntityManagerFactory().createEntityManager()) {
            tx = em.getTransaction();
            tx.begin();
            em.createQuery(UPDATE_DRIVER_AVAILABILITY)
                    .setParameter("driverId", driverId)
                    .executeUpdate();
            tx.commit();
        } catch (Exception e) {
            if (tx != null && tx.isActive()) tx.rollback();
            throw new DBException(Message.Driver.ERROR_WHILE_UPDATING_DRIVER_AVAILABILITY, e);
        }
    }
}