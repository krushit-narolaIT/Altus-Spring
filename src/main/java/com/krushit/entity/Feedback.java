package com.krushit.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(
        name = "feedback",
        uniqueConstraints = @UniqueConstraint(columnNames = {"from_user_id", "ride_id"})
)
public class Feedback {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "feedback_id")
    private int feedbackId;

    @ManyToOne
    @JoinColumn(name = "from_user_id", nullable = false)
    private User fromUser;

    @ManyToOne
    @JoinColumn(name = "to_user_id", nullable = false)
    private User toUser;

    @ManyToOne
    @JoinColumn(name = "ride_id", nullable = false)
    private Ride ride;

    @Column(name = "rating", nullable = false)
    private int rating;

    @Column(name = "comment", columnDefinition = "TEXT")
    private String comment;

    @Column(name = "created_at", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP", updatable = false)
    private LocalDateTime createdAt;

    private Feedback(FeedbackBuilder builder) {
        this.fromUser = builder.fromUserId;
        this.toUser = builder.toUserId;
        this.ride = builder.rideId;
        this.rating = builder.rating;
        this.comment = builder.comment;
    }

    public Feedback() {
    }

    public User getFromUser() {
        return fromUser;
    }

    public int getFeedBack() {
        return feedbackId;
    }

    public User getToUser() {
        return toUser;
    }

    public Ride getRideId() {
        return ride;
    }

    public int getRating() {
        return rating;
    }

    public String getComment() {
        return comment;
    }

    @Override
    public String toString() {
        return "Feedback{" +
                "feedBackId=" + feedbackId +
                ", fromUserId=" + fromUser +
                ", toUserId=" + toUser +
                ", rideId=" + ride +
                ", rating=" + rating +
                ", comment='" + comment + '\'' +
                '}';
    }

    public static class FeedbackBuilder {
        private User fromUserId;
        private User toUserId;
        private Ride rideId;
        private int rating;
        private String comment;

        public FeedbackBuilder setFromUser(User fromUserId) {
            this.fromUserId = fromUserId;
            return this;
        }

        public FeedbackBuilder setToUser(User toUserId) {
            this.toUserId = toUserId;
            return this;
        }

        public FeedbackBuilder setRide(Ride ride) {
            this.rideId = ride;
            return this;
        }

        public FeedbackBuilder setRating(int rating) {
            this.rating = rating;
            return this;
        }

        public FeedbackBuilder setComment(String comment) {
            this.comment = comment;
            return this;
        }

        public Feedback build() {
            return new Feedback(this);
        }
    }
}
