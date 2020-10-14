package com.usermap.entity;

public class Address {
    private String country;
    private String city;
    private String zipcode;
    private String street;

    public Address(String country, String city, String zipcode, String street) {
        this.country = country;
        this.city = city;
        this.zipcode = zipcode;
        this.street = street;
    }

    public String getCountry() {
        return country;
    }

    public String getCity() {
        return city;
    }

    public String getZipcode() {
        return zipcode;
    }

    public String getStreet() {
        return street;
    }
}
