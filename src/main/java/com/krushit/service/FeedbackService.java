package com.krushit.service;

import com.krushit.common.Message;
import com.krushit.common.enums.RideStatus;
import com.krushit.common.exception.ApplicationException;
import com.krushit.dao.FeedbackDAOImpl;
import com.krushit.dao.IFeedbackDAO;
import com.krushit.dto.FeedbackDTO;
import com.krushit.entity.Feedback;
import com.krushit.entity.Ride;
import com.krushit.entity.Role;
import com.krushit.entity.User;
import org.springframework.stereotype.Component;

@Component
public class FeedbackService {
    private final VehicleRideService vehicleRideService = new VehicleRideService();
    private final UserService userService = new UserService();
    private final IFeedbackDAO feedbackDAO = new FeedbackDAOImpl();

    public void submitFeedback(int fromUserId, FeedbackDTO feedbackDTO, int rideId) throws ApplicationException {
        int toUserId = getToUserId(rideId, userService.getUserDetails(fromUserId).get().getRole());
        if (vehicleRideService.getRideStatus(rideId).equals(RideStatus.CANCELLED) || vehicleRideService.getRideStatus(rideId).equals(RideStatus.REJECTED)) {
            throw new ApplicationException(Message.Ride.YOU_CAN_NOT_GIVE_FEEDBACK_TO_CANCELLED_RIDE);
        }
        if (!vehicleRideService.getRideStatus(rideId).equals(RideStatus.COMPLETED)) {
            throw new ApplicationException(Message.Ride.PLEASE_ENTER_FEEDBACK_AFTER_RIDE_COMPLETED);
        }
        int rating = feedbackDTO.getRating();
        String comment = feedbackDTO.getComment();
        if (rating < 1 || rating > 5) {
            throw new ApplicationException(Message.Ride.PLEASE_ENTER_VALID_RATING);
        }
        userService.getUserDetails(fromUserId)
                .orElseThrow(() -> new ApplicationException(Message.User.USER_NOT_FOUND));
        userService.getUserDetails(toUserId)
                .orElseThrow(() -> new ApplicationException(Message.User.USER_NOT_FOUND));
        if (feedbackDAO.isFeedbackGiven(fromUserId, toUserId, rideId)) {
            throw new ApplicationException(Message.FeedBack.FEEDBACK_ALREADY_SUBMITTED);
        }

        User fromUser = userService.getUserDetails(fromUserId).orElseThrow(() ->
                new ApplicationException(Message.User.INVALID_USER_ID));
        User toUser = userService.getUserDetails(toUserId).orElseThrow(() ->
                new ApplicationException(Message.User.INVALID_USER_ID));

        Ride ride = vehicleRideService.getRide(rideId).get();
        Feedback feedback = new Feedback.FeedbackBuilder()
                .setRide(ride)
                .setFromUser(fromUser)
                .setToUser(toUser)
                .setComment(comment)
                .setRating(rating)
                .build();
        feedbackDAO.saveFeedback(feedback);
    }

    public int getToUserId(int rideId, Role userRole) throws ApplicationException {
        int toUserId = feedbackDAO.getRecipientUserIdByRideId(rideId, userRole);
        if (toUserId == 0) {
            throw new ApplicationException(Message.User.INVALID_USER_ID);
        }
        return toUserId;
    }
}
