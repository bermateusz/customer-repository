package com.usermap.service;

import com.usermap.entity.Address;
import com.usermap.entity.Customer;
import com.usermap.entity.SavedCustomer;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;

class CustomerServiceTest {

    Fixtures fixtures = new Fixtures();
    CustomerService customerService = new CustomerService();

    @Test
    void findAllCustomerAfterAddingThem() {
        // when
        customerService.addCustomer(fixtures.customer);
        List<SavedCustomer> customers = customerService.findAll();

        // then
        assertThat(customers).hasSize(3);
    }


    @Test
    void shouldReturnCorrectCustomersAfterAddingThem() {
        // given
        final Address address = new Address("Poland", "Gdańsk", "80-066", "Królewskie Wzgórze");
        final Customer customer1 = new Customer("Michał", "Ciupa", 26, address);
        final Customer customer2 = new Customer("Mateusz", "Bereda", 26, address);

        //when
        customerService.addCustomer(customer1);
        customerService.addCustomer(customer2);
        List<SavedCustomer> customerList = customerService.findAll();

        //then
        assertThat(customerList).extracting("name", "surname", "age","address")
                .contains(tuple("Michał","Ciupa", 26, address),
                        tuple("Mateusz", "Bereda", 26, address));
    }

    @Test
    void addCustomer() {
    }

    @Test
    void modifyCustomer() {
    }

    @Test
    void deleteCustomer() {
    }

    public static class Fixtures {
        Customer customer = new Customer("Mateusz", "Bereda", 23, null);
        UUID uuid = UUID.randomUUID();

    }
}