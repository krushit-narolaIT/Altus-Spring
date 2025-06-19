package com.krushit.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonDeserialize(builder = UpdateUserDTO.UserDTOBuilder.class)
public class UpdateUserDTO {
    private final String firstName;
    private final String lastName;
    private final String phoneNo;
    private final String emailId;

    private UpdateUserDTO(UserDTOBuilder builder) {
        this.firstName = builder.firstName;
        this.lastName = builder.lastName;
        this.phoneNo = builder.phoneNo;
        this.emailId = builder.emailId;
    }

    @JsonPOJOBuilder(withPrefix = "set")
    public static class UserDTOBuilder {
        private String firstName;
        private String lastName;
        private String phoneNo;
        private String emailId;

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

        public UpdateUserDTO build() {
            return new UpdateUserDTO(this);
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

    @Override
    public String toString() {
        return "UserDTO{" +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", phoneNo='" + phoneNo + '\'' +
                ", emailId='" + emailId + '\'' +
                '}';
    }
}