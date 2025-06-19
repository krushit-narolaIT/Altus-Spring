package com.krushit.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonDeserialize(builder = User.UserBuilder.class)
@Inheritance(strategy = InheritanceType.JOINED)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private int userId;

    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;

    @Column(name = "first_name", length = 50, nullable = false)
    private String firstName;

    @Column(name = "last_name", length = 50, nullable = false)
    private String lastName;

    @Column(name = "phone_no", length = 10, nullable = false, unique = true)
    private String phoneNo;

    @Column(name = "email_id", length = 254, nullable = false, unique = true)
    private String emailId;

    @Column(name = "password", length = 20, nullable = false)
    private String password;

    @Column(name = "is_active", nullable = false)
    private boolean isActive;

    @Column(name = "display_id", length = 10, unique = true)
    private String displayId;

    @Column(name = "is_blocked")
    private boolean isBlocked;

    @Column(name = "total_ratings")
    private int totalRatings;

    @Column(name = "rating_count")
    private int ratingCount;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "user_favorites",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "favorite_user_id")
    )
    private Set<User> favoriteUsers = new HashSet<>();

    @ManyToMany(mappedBy = "favoriteUsers")
    private Set<User> favoritedByUsers = new HashSet<>();

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at", insertable = false)
    private LocalDateTime updatedAt;

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public void setBlocked(boolean blocked) {
        isBlocked = blocked;
    }

    public void setRatingCount(int ratingCount) {
        this.ratingCount = ratingCount;
    }

    public void setFavoriteUsers(Set<User> favoriteUsers) {
        this.favoriteUsers = favoriteUsers;
    }

    public void setFavoritedByUsers(Set<User> favoritedByUsers) {
        this.favoritedByUsers = favoritedByUsers;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    protected User(UserBuilder builder) {
        this.userId = builder.userId;
        this.role = builder.role;
        this.firstName = builder.firstName;
        this.lastName = builder.lastName;
        this.phoneNo = builder.phoneNo;
        this.emailId = builder.emailId;
        this.password = builder.password;
        this.isActive = builder.isActive;
        this.displayId = builder.displayId;
        this.isBlocked = builder.isBlocked;
        this.totalRatings = builder.totalRatings;
        this.ratingCount = builder.ratingCount;
        this.createdAt = builder.createdAt;
        this.updatedAt = builder.updatedAt;
        this.favoriteUsers = builder.favoriteUsers;
        this.favoritedByUsers = builder.favoritedByUsers;
    }

    public User() {}

    public int getUserId() {
        return userId;
    }

    public Role getRole() {
        return role;
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

    public boolean isActive() {
        return isActive;
    }

    public String getDisplayId() {
        return displayId;
    }

    public void setDisplayId(String displayId) {
        this.displayId = displayId;
    }

    public boolean isBlocked() {
        return isBlocked;
    }

    public int getTotalRatings() {
        return totalRatings;
    }

    public void setTotalRatings(int totalRatings) {
        this.totalRatings = totalRatings;
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

    public Set<User> getFavoriteUsers() {
        return favoriteUsers;
    }

    public Set<User> getFavoritedByUsers() {
        return favoritedByUsers;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", role=" + role +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", phoneNo='" + phoneNo + '\'' +
                ", emailId='" + emailId + '\'' +
                ", password='" + password + '\'' +
                ", isActive=" + isActive +
                ", displayId='" + displayId + '\'' +
                ", isBlocked=" + isBlocked +
                ", totalRatings=" + totalRatings +
                ", ratingCount=" + ratingCount +
                ", favoriteUsers=" + favoriteUsers +
                ", favoritedByUsers=" + favoritedByUsers +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }

    @JsonPOJOBuilder(withPrefix = "set")
    public static class UserBuilder {
        private int userId;
        private Role role;
        private String firstName;
        private String lastName;
        private String phoneNo;
        private String emailId;
        private String password;
        private boolean isActive;
        private String displayId;
        private boolean isBlocked;
        private int totalRatings;
        private int ratingCount;
        private LocalDateTime createdAt;
        private LocalDateTime updatedAt;
        private Set<User> favoriteUsers = new HashSet<>();
        private Set<User> favoritedByUsers = new HashSet<>();

        public UserBuilder setUserId(int userId) {
            this.userId = userId;
            return this;
        }

        public UserBuilder setRole(Role role) {
            this.role = role;
            return this;
        }

        public UserBuilder setFirstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public UserBuilder setLastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public UserBuilder setPhoneNo(String phoneNo) {
            this.phoneNo = phoneNo;
            return this;
        }

        public UserBuilder setEmailId(String emailId) {
            this.emailId = emailId;
            return this;
        }

        public UserBuilder setPassword(String password) {
            this.password = password;
            return this;
        }

        public UserBuilder setActive(boolean isActive) {
            this.isActive = isActive;
            return this;
        }

        public UserBuilder setDisplayId(String displayId) {
            this.displayId = displayId;
            return this;
        }

        public UserBuilder setIsBlocked(boolean isBlocked) {
            this.isBlocked = isBlocked;
            return this;
        }

        public UserBuilder setTotalRatings(int totalRatings) {
            this.totalRatings = totalRatings;
            return this;
        }

        public UserBuilder setRatingCount(int ratingCount) {
            this.ratingCount = ratingCount;
            return this;
        }

        public UserBuilder setCreatedAt(LocalDateTime createdAt) {
            this.createdAt = createdAt;
            return this;
        }

        public UserBuilder setUpdatedAt(LocalDateTime updatedAt) {
            this.updatedAt = updatedAt;
            return this;
        }

        public UserBuilder setFavoriteUsers(Set<User> favoriteUsers) {
            this.favoriteUsers = favoriteUsers;
            return this;
        }

        public UserBuilder setFavoritedByUsers(Set<User> favoritedByUsers) {
            this.favoritedByUsers = favoritedByUsers;
            return this;
        }

        public User build() {
            return new User(this);
        }
    }
}