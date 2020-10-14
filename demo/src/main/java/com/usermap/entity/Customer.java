package com.usermap.entity;

public class Customer {
    private final String name;
    private final String surname;
    private final int age;
    private final Address address;

    public Customer(String name, String surname, int age, Address address) {
        this.name = name;
        this.surname = surname;
        this.age = age;
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public int getAge() {
        return age;
    }

    public Address getAddress() {
        return address;
    }


}
