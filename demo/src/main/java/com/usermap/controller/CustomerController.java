package com.usermap.controller;

import com.usermap.entity.CustomerDTO;
import com.usermap.entity.SavedCustomer;
import com.usermap.exception.CustomerDoesNotExistException;
import com.usermap.service.CustomerService;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class CustomerController {
    private final CustomerService customerService;


    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping(value = "/api/customers")
    public SavedCustomer addCustomer(@RequestBody CustomerDTO customerDTO) {
        return customerService.addToDB(customerDTO);
    }

    @GetMapping(value = "/api/customers/{id}")
    public SavedCustomer findCustomerByID(@PathVariable Integer id) {
        return Optional.ofNullable(customerService.findCustomerByID(id))
                .orElseThrow(() -> new CustomerDoesNotExistException("Customer with UUID: " + id + " does not exist."));
    }

    @GetMapping(value = "/api/customers")
    public Iterable<SavedCustomer> findAllCustomers() {
        return customerService.findAll();
    }

    @DeleteMapping(value = "/api/customers/{id}")
    public void deleteCustomer(@PathVariable Integer id) {
        try {
            customerService.deleteCustomer(id);
        } catch (CustomerDoesNotExistException e) {
            throw new CustomerDoesNotExistException("Nie istnieje mordo");
        }
    }

    @PostMapping(value = "/api/customers/{id}")
    public SavedCustomer modifyCustomer(@PathVariable Integer id, @RequestBody CustomerDTO customerDTO) {
        return Optional.of(customerService.modifyCustomer(id, customerDTO))
                .orElseThrow(() -> new CustomerDoesNotExistException("Customer with UUID: " + id + " does not exist."));
    }
}
