package com.eindopdracht.garagebedrijf.dto;

import javax.validation.constraints.NotBlank;

public class CarPaperDto {

    @NotBlank
    String fileName;
    @NotBlank
    String contentType;

    @NotBlank
    String url;

    public CarPaperDto(String fileName, String contentType, String url) {
        this.fileName = fileName;
        this.contentType = contentType;
        this.url = url;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }


}
