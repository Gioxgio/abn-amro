package com.abnamro.api.controller;

import com.abnamro.api.request.CustomerRegisterRequest;
import com.abnamro.data.entity.Customer;
import com.abnamro.data.repository.AccountRepository;
import com.abnamro.data.repository.CustomerRepository;
import com.abnamro.mapper.AccountMapper;
import com.abnamro.mapper.CustomerMapper;
import com.abnamro.utils.validator.CustomerRegisterRequestValidator;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@Tag(name = "Customer Controller")
@RequestMapping(path = "/")
public class CustomerController {

    private final AccountMapper accountMapper;
    private final AccountRepository accountRepository;
    private final CustomerMapper customerMapper;
    private final CustomerRegisterRequestValidator customerRegisterRequestValidator;
    private final CustomerRepository customerRepository;

    @Operation(summary = "Allow to register a new customer")
    @PostMapping(value = "/register")
    @Transactional
    public ResponseEntity<Customer> register(@Parameter(description = "Customer data", required = true)
                                             @RequestBody final CustomerRegisterRequest request) {

        customerRegisterRequestValidator.validate(request);

        var customerEntity = customerMapper.fromRegistrateRequest(request);
        customerEntity = customerRepository.saveAndFlush(customerEntity);

        var accountEntity = accountMapper.fromScratch(customerEntity.getId(), request.getAccountType());
        accountRepository.saveAndFlush(accountEntity);

        return ResponseEntity.ok().body(customerEntity);
    }
}
