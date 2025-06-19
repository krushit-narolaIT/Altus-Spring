package com.krushit.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiResponseDTO {
    private String message;
    private String technicalMessage;
    private Object data;

    public ApiResponseDTO(String message, Object data) {
        this.message = message;
        this.data = data;
    }

    public ApiResponseDTO(String message, String technicalMessage, Object data) {
        this.message = message;
        this.technicalMessage = technicalMessage;
        this.data = data;
    }

    public ApiResponseDTO(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getTechnicalMessage() {
        return technicalMessage;
    }

    public void setTechnicalMessage(String technicalMessage) {
        this.technicalMessage = technicalMessage;
    }

    @Override
    public String toString() {
        return "ApiResponse{" +
                "message='" + message + '\'' +
                ", technicalMessage='" + technicalMessage + '\'' +
                ", data=" + data +
                '}';
    }
}
