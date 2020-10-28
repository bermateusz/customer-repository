package com.customer_api.service;

import com.customer_api.entity.CustomerDTO;
import com.customer_api.entity.SavedCustomer;
import com.customer_api.exception.CustomerDoesNotExistException;
import com.customer_api.repository.CustomerRepository;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {
    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public SavedCustomer findCustomerByID(Integer id) {
        return customerRepository.findById(id)
                .orElseThrow(() -> new CustomerDoesNotExistException("Customer with UUID: " + id + " does not exist."));
    }

    public SavedCustomer addToDB(CustomerDTO customerDTO) {
        SavedCustomer savedCustomer = new SavedCustomer(customerDTO);
        customerRepository.save(savedCustomer);
        return savedCustomer;
    }


    public Iterable<SavedCustomer> findAll() {
        return customerRepository.findAll();
    }

    public SavedCustomer modifyCustomer(Integer id, CustomerDTO customerDTO) {
         SavedCustomer customerToUpdate = customerRepository.findById(id)
                .orElseThrow(() -> new CustomerDoesNotExistException("Customer with UUID: " + id + " does not exist."));
        customerToUpdate.setCustomerName(customerDTO.getCustomerName());
        customerToUpdate.setCustomerSurname(customerDTO.getCustomerSurname());
        customerToUpdate.setAge(customerDTO.getAge());
        customerToUpdate.setAddress(customerDTO.getAddress());
        customerToUpdate.setCity(customerDTO.getCity());
        return customerRepository.save(customerToUpdate);
    }

    public void deleteCustomer(Integer id) {
        try {
            customerRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new CustomerDoesNotExistException("Customer with UUID: " + id + " does not exist.");
        }
    }
}



