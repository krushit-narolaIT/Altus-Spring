package com.krushit.dao;

import com.krushit.common.exception.DBException;
import com.krushit.entity.User;
import jakarta.persistence.EntityManager;

import java.util.List;
import java.util.Optional;

public interface IUserDAO {
    void registerUser(User user) throws DBException;
    User getUser(String emailId, String password) throws DBException ;
    boolean isUserExist(String emailID, String phoneNo) throws DBException;
    boolean isValidUser(String emailID, String password) throws DBException;
    boolean isUserExist(int userId) throws DBException;
    Optional<User> getUser(int userId) throws DBException;
    List<User> getAllCustomers() throws DBException;
    String getUserDisplayId(int userId) throws DBException;
    String getUserFullName(int userId) throws DBException;
    void updateUser(User updatedUser) throws DBException;
    Optional<User> getUserByEmail(String email) throws DBException;
    void updatePassword(String email, String newPassword) throws DBException;
    void updateUserRating(int userId, int newRating, EntityManager em) throws DBException;
    void blockUser(int userId) throws DBException;
    boolean isUserBlocked(int userId) throws DBException;
    List<User> getUsersByLowRatingAndReviewCount(int ratingThreshold, int reviewCountThreshold) throws DBException;
    List<User> getUsersByPagination(int offset, int limit) throws DBException;
    void addFavouriteUser(int customerId, int driverId) throws DBException;
    boolean isAlreadyFavourite(int customerId, int driverId) throws DBException;
    void removeFavouriteUser(int customerId, int driverId) throws DBException;
}
