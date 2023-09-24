package com.abnamro.api.controller;

import com.abnamro.data.entity.Account;
import com.abnamro.data.repository.AccountRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@Tag(name = "Account Controller")
@RequestMapping(path = "/")
public class AccountController {

    private final AccountRepository accountRepository;

    @Operation(summary = "Allow to consult an account")
    @GetMapping(value = "/overview/{accountNumber}")
    @Transactional
    public ResponseEntity<Account> overview(@Parameter(description = "Account number", required = true)
                                            @PathVariable final String accountNumber) {

        val accountOptional = accountRepository.getAccountByNumber(accountNumber);

        return accountOptional.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
}
