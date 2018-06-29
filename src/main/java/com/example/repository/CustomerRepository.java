package com.example.repository;

import com.example.entity.Customer;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CustomerRepository extends CrudRepository<Customer, Integer> {

    @Query("SELECT id, name FROM customer WHERE name LIKE :keyword")
    List<Customer> findByCustomerName(/*@Param("keyword")*/ String keyword);
}
