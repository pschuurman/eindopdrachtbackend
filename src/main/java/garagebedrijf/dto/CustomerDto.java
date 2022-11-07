package com.eindopdracht.garagebedrijf.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class CustomerDto {

    @NotNull
    public Long id;

    @NotBlank
    public String firstName;

    @NotBlank
    public String lastName;
    @NotBlank
    public String street;

    @NotNull
    public Short houseNumber;

    @NotBlank
    public String postalCode;

    public CustomerDto(){

    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public short getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(short houseNumber) {
        this.houseNumber = houseNumber;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }
}

