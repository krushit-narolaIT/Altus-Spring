package com.krushit.dto;

public class PendingDriverDTO {
    private final int driverId;
    private final int userId;
    private final String licenceNumber;
    private final String licencePhoto;
    private final boolean documentVerified;
    private final String verificationStatus;
    private final String comment;
    private final String emailId;
    private final String firstName;
    private final String lastName;
    private final String displayId;

    private PendingDriverDTO(PendingDriverDTOBuilder pendingDriverDTOBuilder) {
        this.driverId = pendingDriverDTOBuilder.driverId;
        this.userId = pendingDriverDTOBuilder.userId;
        this.licenceNumber = pendingDriverDTOBuilder.licenceNumber;
        this.licencePhoto = pendingDriverDTOBuilder.licencePhoto;
        this.documentVerified = pendingDriverDTOBuilder.documentVerified;
        this.verificationStatus = pendingDriverDTOBuilder.verificationStatus;
        this.comment = pendingDriverDTOBuilder.comment;
        this.emailId = pendingDriverDTOBuilder.emailId;
        this.firstName = pendingDriverDTOBuilder.firstName;
        this.lastName = pendingDriverDTOBuilder.lastName;
        this.displayId = pendingDriverDTOBuilder.displayId;
    }

    public int getDriverId() { return driverId; }
    public int getUserId() { return userId; }
    public String getLicenceNumber() { return licenceNumber; }
    public String getLicencePhoto() { return licencePhoto; }
    public boolean isDocumentVerified() { return documentVerified; }
    public String getVerificationStatus() { return verificationStatus; }
    public String getComment() { return comment; }
    public String getEmailId() { return emailId; }
    public String getFirstName() { return firstName; }
    public String getLastName() { return lastName; }
    public String getDisplayId() { return displayId; }

    public static class PendingDriverDTOBuilder {
        private int driverId;
        private int userId;
        private String licenceNumber;
        private String licencePhoto;
        private boolean documentVerified;
        private String verificationStatus;
        private String comment;
        private String emailId;
        private String firstName;
        private String lastName;
        private String displayId;

        public PendingDriverDTOBuilder setDriverId(int driverId) {
            this.driverId = driverId;
            return this;
        }

        public PendingDriverDTOBuilder setUserId(int userId) {
            this.userId = userId;
            return this;
        }

        public PendingDriverDTOBuilder setLicenceNumber(String licenceNumber) {
            this.licenceNumber = licenceNumber;
            return this;
        }

        public PendingDriverDTOBuilder setLicencePhoto(String licencePhoto) {
            this.licencePhoto = licencePhoto;
            return this;
        }

        public PendingDriverDTOBuilder setDocumentVerified(boolean documentVerified) {
            this.documentVerified = documentVerified;
            return this;
        }

        public PendingDriverDTOBuilder setVerificationStatus(String verificationStatus) {
            this.verificationStatus = verificationStatus;
            return this;
        }

        public PendingDriverDTOBuilder setComment(String comment) {
            this.comment = comment;
            return this;
        }

        public PendingDriverDTOBuilder setEmailId(String emailId) {
            this.emailId = emailId;
            return this;
        }

        public PendingDriverDTOBuilder setFirstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public PendingDriverDTOBuilder setLastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public PendingDriverDTOBuilder setDisplayId(String displayId) {
            this.displayId = displayId;
            return this;
        }

        public PendingDriverDTO build() {
            return new PendingDriverDTO(this);
        }
    }
}