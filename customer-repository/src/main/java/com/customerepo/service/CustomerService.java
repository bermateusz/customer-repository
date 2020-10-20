package com.usermap.service;

import com.usermap.entity.CustomerDTO;
import com.usermap.entity.SavedCustomer;
import com.usermap.exception.CustomerDoesNotExistException;
import com.usermap.repository.CustomerRepository;
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
        } catch (CustomerDoesNotExistException e) {
            throw new CustomerDoesNotExistException("Customer with UUID: " + id + " does not exist.");
        }
    }
}



