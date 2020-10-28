package com.customer_api.controller;

import com.customer_api.entity.CustomerDTO;
import com.customer_api.entity.SavedCustomer;
import com.customer_api.service.CustomerService;
import org.springframework.web.bind.annotation.*;

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
        return customerService.findCustomerByID(id);
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
