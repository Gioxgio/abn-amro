package com.abnamro.data.repository;

import com.abnamro.data.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, String> {

    boolean existsByUsername(String username);
}
