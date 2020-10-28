package com.customer_api.repository;

import com.customer_api.entity.SavedCustomer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CustomerRepository extends CrudRepository<SavedCustomer, Integer>{
}
