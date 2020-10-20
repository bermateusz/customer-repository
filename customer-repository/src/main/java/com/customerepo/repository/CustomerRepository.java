package com.usermap.repository;

import com.usermap.entity.SavedCustomer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CustomerRepository extends CrudRepository<SavedCustomer, Integer>{
}
