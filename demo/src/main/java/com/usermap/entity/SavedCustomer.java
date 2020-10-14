package com.usermap.entity;

import java.util.UUID;

public class SavedCustomer {
    private final UUID uuid;
    private final String name;
    private final String surname;
    private final int age;
    private final Address address;

    public SavedCustomer(UUID uuid, Customer customer) {
        this.uuid = uuid;
        this.name = customer.getName();
        this.surname = customer.getSurname();
        this.age = customer.getAge();
        this.address = customer.getAddress();
    }

    public UUID getUuid() {
        return uuid;
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
