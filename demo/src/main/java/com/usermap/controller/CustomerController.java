package com.usermap.controller;

import com.usermap.entity.CustomerDTO;
import com.usermap.entity.SavedCustomer;
import com.usermap.exception.CustomerDoesNotExistException;
import com.usermap.service.CustomerService;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping(value = "/customers")
public class CustomerController {
    private final CustomerService customerService;


    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @RequestMapping(method = RequestMethod.POST)
    public SavedCustomer addCustomer(@RequestBody CustomerDTO customerDTO) {
        return customerService.addToDB(customerDTO);
    }

    @GetMapping(value = "/{id}")
    public SavedCustomer findCustomerByID(@PathVariable Integer id) {
        return Optional.ofNullable(customerService.findCustomerByID(id))
                .orElseThrow(() -> new CustomerDoesNotExistException("Customer with UUID: " + id + " does not exist."));
    }

    @RequestMapping(method = RequestMethod.GET)
    public Iterable<SavedCustomer> findAllCustomers() {
        return customerService.findAll();
    }

    @DeleteMapping(value = "/{id}")
    public void deleteCustomer(@PathVariable Integer id) {
        customerService.deleteCustomer(id);
    }

    @PutMapping(value = "/{id}")
    public SavedCustomer modifyCustomer(@PathVariable Integer id, @RequestBody CustomerDTO customerDTO) {
        return customerService.modifyCustomer(id, customerDTO);
    }
}
