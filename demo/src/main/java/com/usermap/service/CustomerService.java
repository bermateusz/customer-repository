package com.usermap.service;

import com.usermap.entity.Customer;
import com.usermap.exception.CustomerDoesNotExistException;
import com.usermap.entity.SavedCustomer;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class CustomerService {
    private static final Map<UUID, Customer> CUSTOMER_MAP = new HashMap<>();

    public Customer findCustomer(UUID uuid) {
        return Optional.ofNullable(CUSTOMER_MAP.get(uuid))
                .orElseThrow(() -> new CustomerDoesNotExistException("Customer with UUID: " + uuid + " does not exist."));
    }

    public List<SavedCustomer> findAll() {
        return CUSTOMER_MAP.entrySet().stream()
                 .map(entry -> new SavedCustomer(entry.getKey(), entry.getValue()))
                 .collect(Collectors.toList());
    }

    public Map<UUID, Customer> getCustomerMap() {
        return CUSTOMER_MAP;
    }

    public SavedCustomer addCustomer(Customer customer) {
        final UUID uuid = UUID.randomUUID();
        CUSTOMER_MAP.put(uuid, customer);
        return new SavedCustomer(uuid, customer);
    }

    public void modifyCustomer(UUID uuid, Customer customer) {
        if (!CUSTOMER_MAP.containsKey(uuid)) {
            throw new CustomerDoesNotExistException("Customer with UUID: " + uuid + " does not exist.");
        } else {
            CUSTOMER_MAP.replace(uuid, customer);
        }
    }

    public void deleteCustomer(UUID uuid) {
        if (!CUSTOMER_MAP.containsKey(uuid)) {
            throw new CustomerDoesNotExistException("Customer with UUID: " + uuid + " does not exist.");
        } else {
            CUSTOMER_MAP.remove(uuid);
        }
    }


}
