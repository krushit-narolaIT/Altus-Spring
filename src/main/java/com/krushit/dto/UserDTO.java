package com.krushit.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import com.krushit.entity.Role;

import java.time.LocalDateTime;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonDeserialize(builder = UserDTO.UserDTOBuilder.class)
public class UserDTO {
    private final int userId;
    private final Role roleType;
    private final String firstName;
    private final String lastName;
    private final String phoneNo;
    private final String emailId;
    private final String password;
    private final String displayId;
    private final boolean isActive;
    private final boolean isBlocked;
    private final int totalRatings;
    private final int ratingCount;
    private final LocalDateTime createdAt;
    private final LocalDateTime updatedAt;

    private UserDTO(UserDTOBuilder builder) {
        this.userId = builder.userId;
        this.roleType = builder.roleType;
        this.firstName = builder.firstName;
        this.lastName = builder.lastName;
        this.phoneNo = builder.phoneNo;
        this.emailId = builder.emailId;
        this.password = builder.password;
        this.displayId = builder.displayId;
        this.isActive = builder.isActive;
        this.isBlocked = builder.isBlocked;
        this.totalRatings = builder.totalRatings;
        this.ratingCount = builder.ratingCount;
        this.createdAt = builder.createdAt;
        this.updatedAt = builder.updatedAt;
    }

    @JsonPOJOBuilder(withPrefix = "set")
    public static class UserDTOBuilder {
        private int userId;
        private Role roleType;
        private String firstName;
        private String lastName;
        private String phoneNo;
        private String emailId;
        private String password;
        private String displayId;
        private boolean isActive;
        private boolean isBlocked;
        private int totalRatings;
        private int ratingCount;
        private LocalDateTime createdAt;
        private LocalDateTime updatedAt;

        public UserDTOBuilder setUserId(int userId) {
            this.userId = userId;
            return this;
        }

        public UserDTOBuilder setRole(Role roleType) {
            this.roleType = roleType;
            return this;
        }

        public UserDTOBuilder setFirstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public UserDTOBuilder setLastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public UserDTOBuilder setPhoneNo(String phoneNo) {
            this.phoneNo = phoneNo;
            return this;
        }

        public UserDTOBuilder setEmailId(String emailId) {
            this.emailId = emailId;
            return this;
        }

        public UserDTOBuilder setPassword(String password) {
            this.password = password;
            return this;
        }

        public UserDTOBuilder setDisplayId(String displayId) {
            this.displayId = displayId;
            return this;
        }

        public UserDTOBuilder setIsActive(boolean isActive) {
            this.isActive = isActive;
            return this;
        }

        public UserDTOBuilder setIsBlocked(boolean isBlocked) {
            this.isBlocked = isBlocked;
            return this;
        }

        public UserDTOBuilder setTotalRatings(int totalRatings) {
            this.totalRatings = totalRatings;
            return this;
        }

        public UserDTOBuilder setRatingCount(int ratingCount) {
            this.ratingCount = ratingCount;
            return this;
        }

        public UserDTOBuilder setCreatedAt(LocalDateTime createdAt) {
            this.createdAt = createdAt;
            return this;
        }

        public UserDTOBuilder setUpdatedAt(LocalDateTime updatedAt) {
            this.updatedAt = updatedAt;
            return this;
        }

        public UserDTO build() {
            return new UserDTO(this);
        }
    }

    public int getUserId() {
        return userId;
    }

    public Role getRole() {
        return roleType;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public String getEmailId() {
        return emailId;
    }

    public String getPassword() {
        return password;
    }

    public String getDisplayId() {
        return displayId;
    }

    public boolean isActive() {
        return isActive;
    }

    public boolean isBlocked() {
        return isBlocked;
    }

    public int getTotalRatings() {
        return totalRatings;
    }

    public int getRatingCount() {
        return ratingCount;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    @Override
    public String toString() {
        return "UserDTO{" +
                "userId=" + userId +
                ", role=" + roleType +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", phoneNo='" + phoneNo + '\'' +
                ", emailId='" + emailId + '\'' +
                ", displayId='" + displayId + '\'' +
                ", isActive=" + isActive +
                ", isBlocked=" + isBlocked +
                ", totalRatings=" + totalRatings +
                ", ratingCount=" + ratingCount +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}