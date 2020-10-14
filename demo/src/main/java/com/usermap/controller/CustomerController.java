package com.usermap.controller;

import com.usermap.entity.Customer;
import com.usermap.exception.CustomerDoesNotExistException;
import com.usermap.service.CustomerService;
import com.usermap.entity.SavedCustomer;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
public class CustomerController {
    private final CustomerService customerService;


    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping(value = "/api/customers")
    public SavedCustomer addCustomer(@RequestBody Customer customer) {
       return customerService.addCustomer(customer);
    }

    @GetMapping(value = "/api/customers")
    public List<SavedCustomer> returnCustomersAsList() {
        return customerService.findAll();
    }

    @PutMapping(value = "/api/customers/{uuid}")
    public void modifyCustomer(@PathVariable UUID uuid, @RequestBody Customer customer) {
        customerService.modifyCustomer(uuid, customer);
    }

    @DeleteMapping(value = "/api/customers/{uuid}")
    public void deleteCustomer(@PathVariable UUID uuid) {
        customerService.deleteCustomer(uuid);
    }

    @GetMapping(value = "/api/customers/{uuid}")
    public Customer returnCustomer(@PathVariable UUID uuid){
        return Optional.ofNullable(customerService.findCustomer(uuid))
                .orElseThrow(() -> new CustomerDoesNotExistException("Customer with UUID: " + uuid + " does not exist."));
//        return this.customerService.findCustomer(uuid).orElseThrow(() -> new CustomerDoesNotExistException());
    }


}
