package com.krushit.dao;

import com.krushit.common.Message;
import com.krushit.common.config.JPAConfig;
import com.krushit.common.enums.DocumentVerificationStatus;
import com.krushit.common.enums.RoleType;
import com.krushit.common.exception.DBException;
import com.krushit.entity.Driver;
import com.krushit.entity.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class UserDAOImpl implements IUserDAO {
    @PersistenceContext
    private EntityManager entityManager;

    private static final String GET_USER_BY_EMAIL_AND_PASSWORD =
            "SELECT u FROM User u WHERE u.emailId = :email AND u.password = :password";
    private static final String VALIDATE_USER =
            "SELECT COUNT(u) FROM User u WHERE u.emailId = :email AND u.password = :password";
    private static final String CHECK_USER_EXISTENCE_BY_EMAIL_OR_PHONE =
            "SELECT COUNT(u) FROM User u WHERE u.emailId = :email OR u.phoneNo = :phone";
    private static final String GET_ALL_CUSTOMERS =
            "SELECT u FROM User u WHERE u.role.role = :roleType";
    private static final String GET_DISPLAY_ID_BY_USER_ID =
            "SELECT u.displayId FROM User u WHERE u.userId = :userId";
    private static final String GET_USER_NAME_BY_USER_ID =
            "SELECT u.firstName, u.lastName FROM User u WHERE u.userId = :userId";
    private static final String GET_USER_BY_EMAIL =
            "SELECT u FROM User u WHERE u.emailId = :email";
    private static final String UPDATE_PASSWORD_BY_EMAIL =
            "UPDATE User u SET u.password = :pwd WHERE u.emailId = :email";
    private static final String UPDATE_USER_RATING =
            "UPDATE User u SET u.totalRatings = ((u.totalRatings * u.ratingCount) + :rating) / (u.ratingCount + 1), " +
                    "u.ratingCount = u.ratingCount + 1 WHERE u.userId = :userId";
    private static final String BLOCK_USER_BY_ID =
            "UPDATE User u SET u.isBlocked = true WHERE u.userId = :id";
    private static final String IS_USER_BLOCKED =
            "SELECT u.isBlocked FROM User u WHERE u.userId = :id";
    private static final String GET_USERS_BY_LOW_RATING =
            "SELECT u FROM User u WHERE u.totalRatings < :rating AND u.ratingCount < :reviews";
    private static final String GET_USERS_PAGINATED =
            "SELECT u FROM User u";

    @Override
    @Transactional
    public void registerUser(User user) throws DBException {
        try {
            entityManager.persist(user);
            entityManager.flush();
            String displayId = (user.getRole().getRoleType().equals(RoleType.ROLE_DRIVER))
                    ? generateDriverDisplayId(user.getUserId())
                    : generateCustomerDisplayId(user.getUserId());
            user.setDisplayId(displayId);
            entityManager.merge(user);
            if (user.getRole().getRoleType().equals(RoleType.ROLE_DRIVER)) {
                Driver driver = new Driver.DriverBuilder()
                        .setUser(user)
                        .setVerificationStatus(DocumentVerificationStatus.INCOMPLETE)
                        .build();
                entityManager.persist(driver);
            }
        } catch (Exception e) {
            throw new DBException(Message.User.ERROR_WHILE_REGISTERING_USER, e);
        }
    }

    private String generateCustomerDisplayId(int userId) {
        String timestampPart = String.valueOf(System.currentTimeMillis() % 1000);
        String userIdPart = String.format("%04d", userId % 10000);
        return "US" + userIdPart + "R" + timestampPart;
    }

    private String generateDriverDisplayId(int userId) {
        String timestampPart = String.valueOf(System.currentTimeMillis() % 1000);
        String userIdPart = String.format("%04d", userId % 10000);
        return "DR" + userIdPart + "V" + timestampPart;
    }

    @Override
    public User getUser(String emailId, String password) throws DBException {
        try {
            TypedQuery<User> query = entityManager.createQuery(GET_USER_BY_EMAIL_AND_PASSWORD, User.class);
            query.setParameter("email", emailId);
            query.setParameter("password", password);
            List<User> result = query.getResultList();
            return result.isEmpty() ? null : result.get(0);
        } catch (Exception e) {
            throw new DBException(Message.User.ERROR_WHILE_USER_LOGIN, e);
        }
    }

    @Override
    public boolean isValidUser(String emailID, String password) throws DBException {
        try {
            Long count = entityManager.createQuery(VALIDATE_USER, Long.class)
                    .setParameter("email", emailID)
                    .setParameter("password", password)
                    .getSingleResult();
            return count > 0;
        } catch (Exception e) {
            throw new DBException(Message.User.ERROR_WHILE_VALIDATING_USER, e);
        }
    }

    @Override
    public boolean isUserExist(String emailID, String phoneNo) throws DBException {
        try {
            Long count = entityManager.createQuery(CHECK_USER_EXISTENCE_BY_EMAIL_OR_PHONE, Long.class)
                    .setParameter("email", emailID)
                    .setParameter("phone", phoneNo)
                    .getSingleResult();
            return count > 0;
        } catch (Exception e) {
            throw new DBException(Message.User.ERROR_WHILE_CHECKING_USER_EXISTENCE, e);
        }
    }

    @Override
    public boolean isUserExist(int userId) throws DBException {
        try {
            return entityManager.find(User.class, userId) != null;
        } catch (Exception e) {
            throw new DBException(Message.User.ERROR_WHILE_CHECKING_USER_EXISTENCE, e);
        }
    }

    @Override
    public Optional<User> getUser(int userId) throws DBException {
        try {
            return Optional.ofNullable(entityManager.find(User.class, userId));
        } catch (Exception e) {
            throw new DBException(Message.User.ERROR_WHILE_GET_USER_DETAILS, e);
        }
    }

    @Override
    public List<User> getAllCustomers() throws DBException {
        try {
            return entityManager.createQuery(GET_ALL_CUSTOMERS, User.class)
                    .setParameter("roleType", RoleType.ROLE_CUSTOMER)
                    .getResultList();
        } catch (Exception e) {
            throw new DBException(Message.User.ERROR_WHILE_GETTING_ALL_CUSTOMERS, e);
        }
    }

    @Override
    public String getUserDisplayId(int userId) throws DBException {
        try {
            return entityManager.createQuery(GET_DISPLAY_ID_BY_USER_ID, String.class)
                    .setParameter("userId", userId)
                    .getSingleResult();
        } catch (Exception e) {
            throw new DBException(Message.User.ERROR_WHILE_GETTING_DISPLAY_ID, e);
        }
    }

    @Override
    public String getUserFullName(int userId) throws DBException {
        try {
            Object[] result = entityManager.createQuery(GET_USER_NAME_BY_USER_ID, Object[].class)
                    .setParameter("userId", userId)
                    .getSingleResult();
            return result[0] + " " + result[1];
        } catch (Exception e) {
            throw new DBException(Message.User.ERROR_WHILE_GETTING_USER_FULL_NAME, e);
        }
    }

    @Override
    @Transactional
    public void updateUser(User user) throws DBException {
        try {
            entityManager.merge(user);
        } catch (Exception e) {
            throw new DBException(Message.User.ERROR_WHILE_UPDATING_USER_DETAILS, e);
        }
    }

    @Override
    public Optional<User> getUserByEmail(String email) throws DBException {
        try {
            return entityManager.createQuery(GET_USER_BY_EMAIL, User.class)
                    .setParameter("email", email)
                    .getResultStream()
                    .findFirst();
        } catch (Exception e) {
            throw new DBException(Message.User.ERROR_WHILE_GET_USER_DETAILS, e);
        }
    }

    @Override
    @Transactional
    public void updatePassword(String email, String newPassword) throws DBException {
        try {
            entityManager.createQuery(UPDATE_PASSWORD_BY_EMAIL)
                    .setParameter("pwd", newPassword)
                    .setParameter("email", email)
                    .executeUpdate();
        } catch (Exception e) {
            throw new DBException(Message.User.ERROR_WHILE_UPDATING_PASSWORD, e);
        }
    }

    @Override
    @Transactional
    public void updateUserRating(int userId, int rating, EntityManager em) throws DBException {
        try {
            em.createQuery(UPDATE_USER_RATING)
                    .setParameter("rating", rating)
                    .setParameter("userId", userId)
                    .executeUpdate();
        } catch (Exception e) {
            throw new DBException(Message.User.ERROR_WHILE_UPDATE_DRIVER_RATING, e);
        }
    }

    @Override
    @Transactional
    public void blockUser(int userId) throws DBException {
        try {
            entityManager.createQuery(BLOCK_USER_BY_ID)
                    .setParameter("id", userId)
                    .executeUpdate();
        } catch (Exception e) {
            throw new DBException(Message.User.ERROR_WHILE_BLOCKING_USER, e);
        }
    }

    @Override
    public boolean isUserBlocked(int userId) throws DBException {
        try {
            return entityManager.createQuery(IS_USER_BLOCKED, Boolean.class)
                    .setParameter("id", userId)
                    .getSingleResult();
        } catch (Exception e) {
            throw new DBException(Message.User.ERROR_WHILE_CHECK_IS_USER_BLOCKED, e);
        }
    }

    @Override
    public List<User> getUsersByLowRatingAndReviewCount(int ratingThreshold, int reviewCountThreshold) throws DBException {
        try {
            return entityManager.createQuery(GET_USERS_BY_LOW_RATING, User.class)
                    .setParameter("rating", ratingThreshold)
                    .setParameter("reviews", reviewCountThreshold)
                    .getResultList();
        } catch (Exception e) {
            throw new DBException(Message.User.ERROR_WHILE_GETTING_CUSTOMERS_BY_RATING, e);
        }
    }

    @Override
    public List<User> getUsersByPagination(int offset, int limit) throws DBException {
        try {
            return entityManager.createQuery(GET_USERS_PAGINATED, User.class)
                    .setFirstResult(offset)
                    .setMaxResults(limit)
                    .getResultList();
        } catch (Exception e) {
            throw new DBException(Message.User.ERROR_WHILE_GETTING_CUSTOMERS_BY_RATING, e);
        }
    }

    @Override
    @Transactional
    public void addFavouriteUser(int customerId, int driverId) throws DBException {
        try {
            User customer = entityManager.find(User.class, customerId);
            User driver = entityManager.find(User.class, driverId);
            customer.getFavoriteUsers().add(driver);
            entityManager.merge(customer);
        } catch (Exception e) {
            throw new DBException(Message.User.ERROR_WHILE_ADDING_FAVOURITE_DRIVER, e);
        }
    }

    @Override
    public boolean isAlreadyFavourite(int customerId, int driverId) throws DBException {
        try {
            User customer = entityManager.find(User.class, customerId);
            User driver = entityManager.find(User.class, driverId);
            return customer.getFavoriteUsers().contains(driver);
        } catch (Exception e) {
            throw new DBException(Message.User.ERROR_WHILE_VALIDATING_FAVOURITE_DRIVER, e);
        }
    }

    @Override
    @Transactional
    public void removeFavouriteUser(int customerId, int driverId) throws DBException {
        try {
            User customer = entityManager.find(User.class, customerId);
            User driver = entityManager.find(User.class, driverId);
            customer.getFavoriteUsers().remove(driver);
            entityManager.merge(customer);
        } catch (Exception e) {
            throw new DBException(Message.User.ERROR_WHILE_DELETING_FAVOURITE_DRIVER, e);
        }
    }
}
