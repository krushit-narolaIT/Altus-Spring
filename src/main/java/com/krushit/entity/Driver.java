package com.krushit.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import com.krushit.common.enums.DocumentVerificationStatus;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "drivers")
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonDeserialize(builder = Driver.DriverBuilder.class)
@NamedQueries(
        @NamedQuery(
                name = "Driver.findByLicenceNumber",
                query = "SELECT d.driverId FROM Driver d WHERE d.licenceNumber = :licenceNumber"
        )
)
public class Driver {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "driver_id")
    private int driverId;

    @OneToOne
    @JoinColumn(name = "user_id", unique = true)
    private User user;

    @Column(name = "licence_number", length = 15, unique = true)
    private String licenceNumber;

    @Column(name = "is_document_verified", nullable = false)
    private boolean isDocumentVerified = false;

    @Column(name = "licence_photo", length = 255)
    private String licencePhoto;

    @Column(name = "is_available")
    private boolean isAvailable;

    @Enumerated(EnumType.STRING)
    @Column(name = "verification_status")
    private DocumentVerificationStatus verificationStatus;

    @Column(name = "comment", length = 254)
    private String comment;

    @Column(name = "created_at", updatable = false)
    @CreationTimestamp
    private LocalDateTime createdAt;

    @Column(name = "updated_at", insertable = false)
    @UpdateTimestamp
    private LocalDateTime updatedAt;

    public Driver() {
    }

    private Driver(DriverBuilder builder) {
        this.driverId = builder.driverId;
        this.user = builder.user;
        this.licenceNumber = builder.licenceNumber;
        this.isDocumentVerified = builder.isDocumentVerified;
        this.licencePhoto = builder.licencePhoto;
        this.isAvailable = builder.isAvailable;
        this.verificationStatus = builder.verificationStatus;
        this.comment = builder.comment;
        this.createdAt = builder.createdAt;
        this.updatedAt = builder.updatedAt;
    }

    public int getDriverId() {
        return driverId;
    }

    public User getUser() {
        return user;
    }


    public String getLicenceNumber() {
        return licenceNumber;
    }

    public boolean isDocumentVerified() {
        return isDocumentVerified;
    }

    public String getLicencePhoto() {
        return licencePhoto;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public DocumentVerificationStatus getVerificationStatus() {
        return verificationStatus;
    }

    public String getComment() {
        return comment;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    @Override
    public String toString() {
        return "Driver{" +
                "driverId=" + driverId +
                ", licenceNumber='" + licenceNumber + '\'' +
                ", isDocumentVerified=" + isDocumentVerified +
                ", licencePhoto='" + licencePhoto + '\'' +
                ", isAvailable=" + isAvailable +
                ", verificationStatus='" + verificationStatus + '\'' +
                ", comment='" + comment + '\'' +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }

    @JsonPOJOBuilder(withPrefix = "set")
    public static class DriverBuilder {
        private int driverId;
        private User user;
        private String licenceNumber;
        private boolean isDocumentVerified;
        private String licencePhoto;
        private boolean isAvailable;
        private DocumentVerificationStatus verificationStatus;
        private String comment;
        private LocalDateTime createdAt;
        private LocalDateTime updatedAt;

        public DriverBuilder setDriverId(int driverId) {
            this.driverId = driverId;
            return this;
        }

        public DriverBuilder setUser(User user) {
            this.user = user;
            return this;
        }

        public DriverBuilder setLicenceNumber(String licenceNumber) {
            this.licenceNumber = licenceNumber;
            return this;
        }

        public DriverBuilder setDocumentVerified(boolean isDocumentVerified) {
            this.isDocumentVerified = isDocumentVerified;
            return this;
        }

        public DriverBuilder setLicencePhoto(String licencePhoto) {
            this.licencePhoto = licencePhoto;
            return this;
        }

        public DriverBuilder setAvailable(boolean isAvailable) {
            this.isAvailable = isAvailable;
            return this;
        }

        public DriverBuilder setVerificationStatus(DocumentVerificationStatus verificationStatus) {
            this.verificationStatus = verificationStatus;
            return this;
        }

        public DriverBuilder setComment(String comment) {
            this.comment = comment;
            return this;
        }

        public DriverBuilder setCreatedAt(LocalDateTime createdAt) {
            this.createdAt = createdAt;
            return this;
        }

        public DriverBuilder setUpdatedAt(LocalDateTime updatedAt) {
            this.updatedAt = updatedAt;
            return this;
        }

        public Driver build() {
            return new Driver(this);
        }
    }
}
