package com.bank.data.repository;

import com.bank.data.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, String> {

    Optional<Account> getAccountByNumber(String accountNumber);
}
