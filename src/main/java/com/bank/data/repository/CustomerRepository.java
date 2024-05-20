package com.bank.data.repository;

import com.bank.data.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, String> {

    boolean existsByUsername(String username);

    boolean existsByUsernameAndPassword(String username, String password);
}
