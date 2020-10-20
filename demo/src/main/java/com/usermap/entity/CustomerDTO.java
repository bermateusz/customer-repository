package com.usermap.entity;

public class CustomerDTO {

    private String customerName;
    private String customerSurname;
    private int age;
    private String address;
    private String city;

    public CustomerDTO() {
    }

    public CustomerDTO(String customerName, String customerSurname, int age, String address, String city) {
        this.customerName = customerName;
        this.customerSurname = customerSurname;
        this.age = age;
        this.address = address;
        this.city = city;
    }

    public String getCustomerName() {
        return customerName;
    }

    public String getCustomerSurname() {
        return customerSurname;
    }

    public int getAge() {
        return age;
    }

    public String getAddress() {
        return address;
    }

    public String getCity() {
        return city;
    }
}
