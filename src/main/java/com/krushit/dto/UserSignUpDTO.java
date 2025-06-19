package com.krushit.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

@JsonDeserialize(builder = UserSignUpDTO.UserSignUpDTOBuilder.class)
public class UserSignUpDTO {
    private final String firstName;
    private final String lastName;
    private final String phoneNo;
    private final String emailId;
    private final String password;

    private UserSignUpDTO(UserSignUpDTOBuilder builder) {
        this.firstName = builder.firstName;
        this.lastName = builder.lastName;
        this.phoneNo = builder.phoneNo;
        this.emailId = builder.emailId;
        this.password = builder.password;
    }

    @JsonPOJOBuilder(withPrefix = "set")
    public static class UserSignUpDTOBuilder {
        private String firstName;
        private String lastName;
        private String phoneNo;
        private String emailId;
        private String password;

        public UserSignUpDTOBuilder setFirstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public UserSignUpDTOBuilder setLastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public UserSignUpDTOBuilder setPhoneNo(String phoneNo) {
            this.phoneNo = phoneNo;
            return this;
        }

        public UserSignUpDTOBuilder setEmailId(String emailId) {
            this.emailId = emailId;
            return this;
        }

        public UserSignUpDTOBuilder setPassword(String password) {
            this.password = password;
            return this;
        }

        public UserSignUpDTO build() {
            return new UserSignUpDTO(this);
        }
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

    @Override
    public String toString() {
        return "UserSignUpDTO{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", phoneNo='" + phoneNo + '\'' +
                ", emailId='" + emailId + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}