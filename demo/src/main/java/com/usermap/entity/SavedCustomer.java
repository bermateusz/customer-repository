package com.usermap.entity;

import javax.persistence.*;

@Entity
@Table(name = "customer")
public class SavedCustomer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String customerName;
    private String customerSurname;
    private int age;
    private String address;
    private String city;

    public SavedCustomer() {
    }

    public SavedCustomer(CustomerDTO customerDTO) {
        this.customerName = customerDTO.getCustomerName();
        this.customerSurname = customerDTO.getCustomerSurname();
        this.age = customerDTO.getAge();
        this.address = customerDTO.getAddress();
        this.city = customerDTO.getCity();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerSurname() {
        return customerSurname;
    }

    public void setCustomerSurname(String customerSurname) {
        this.customerSurname = customerSurname;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}