package com.krushit.dto;

import jakarta.servlet.http.Part;

public class DriverDocumentDTO {

    private String licenceNumber;
    private Part licencePhoto;

    public DriverDocumentDTO() {
    }

    public DriverDocumentDTO(String licenceNumber, Part licencePhoto) {
        this.licenceNumber = licenceNumber;
        this.licencePhoto = licencePhoto;
    }

    public String getLicenceNumber() {
        return licenceNumber;
    }

    public void setLicenceNumber(String licenceNumber) {
        this.licenceNumber = licenceNumber;
    }

    public Part getLicencePhoto() {
        return licencePhoto;
    }

    public void setLicencePhoto(Part licencePhoto) {
        this.licencePhoto = licencePhoto;
    }
}
