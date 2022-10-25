package com.eindopdracht.garagebedrijf.dto;

public class CustomerDto {

    public Long id;

    public String firstName;

    public String lastName;

    public String street;

    public Short houseNumber;

    public String postalCode;

    public CustomerDto() {

    }

    public CustomerDto(Long id, String firstName, String lastName, String street, Short houseNumber, String postalCode) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.street = street;
        this.houseNumber = houseNumber;
        this.postalCode = postalCode;
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

