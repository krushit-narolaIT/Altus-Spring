package com.krushit.dao;

import com.krushit.common.exception.DBException;
import com.krushit.entity.Feedback;
import com.krushit.entity.Role;

public interface IFeedbackDAO {
    void saveFeedback(Feedback feedback) throws DBException;
    int getRecipientUserIdByRideId(int rideId, Role userRoleType) throws DBException;
    boolean isFeedbackGiven(int fromUserId, int toUserId, int rideId) throws DBException;
}
