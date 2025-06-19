package com.krushit.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

@JsonDeserialize(builder = ChangePasswordDTO.ChangePasswordDTOBuilder.class)
public class ChangePasswordDTO {
    private final String emailId;
    private final String oldPassword;
    private final String newPassword;

    private ChangePasswordDTO(ChangePasswordDTOBuilder builder) {
        this.emailId = builder.emailId;
        this.oldPassword = builder.oldPassword;
        this.newPassword = builder.newPassword;
    }

    public String getEmailId() {
        return emailId;
    }

    public String getOldPassword() {
        return oldPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    @JsonPOJOBuilder(withPrefix = "set")
    public static class ChangePasswordDTOBuilder {
        private String emailId;
        private String oldPassword;
        private String newPassword;

        public ChangePasswordDTOBuilder setEmailId(String emailId) {
            this.emailId = emailId;
            return this;
        }

        public ChangePasswordDTOBuilder setOldPassword(String oldPassword) {
            this.oldPassword = oldPassword;
            return this;
        }

        public ChangePasswordDTOBuilder setNewPassword(String newPassword) {
            this.newPassword = newPassword;
            return this;
        }

        public ChangePasswordDTO build() {
            return new ChangePasswordDTO(this);
        }
    }
}
