package com.bank.api.controller;

import com.bank.api.request.CustomerLogonRequest;
import com.bank.api.request.CustomerRegisterRequest;
import com.bank.api.response.CustomerRegisterResponse;
import com.bank.data.repository.AccountRepository;
import com.bank.data.repository.CustomerRepository;
import com.bank.mapper.AccountMapper;
import com.bank.mapper.CustomerMapper;
import com.bank.utils.validator.CustomerRequestValidator;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.http.HttpStatus;
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
    private final CustomerRequestValidator customerRequestValidator;
    private final CustomerRepository customerRepository;

    @Operation(summary = "Allow to register a new customer")
    @PostMapping(value = "/register")
    @Transactional
    public ResponseEntity<CustomerRegisterResponse> register(@Parameter(description = "Customer data", required = true)
                                                             @RequestBody final CustomerRegisterRequest request) {

        customerRequestValidator.validate(request);

        var customerEntity = customerMapper.fromRegistrateRequest(request);
        customerEntity = customerRepository.saveAndFlush(customerEntity);

        var accountEntity = accountMapper.fromScratch(customerEntity.getId(), request.getAccountType());
        accountRepository.saveAndFlush(accountEntity);

        return ResponseEntity.ok().body(new CustomerRegisterResponse(accountEntity, customerEntity));
    }

    @Operation(summary = "Verify customer credentials")
    @PostMapping(value = "/logon")
    @Transactional
    public ResponseEntity<String> logon(@Parameter(description = "Customer credentials", required = true)
                                        @RequestBody final CustomerLogonRequest request) {

        customerRequestValidator.validate(request);

        val result = customerRepository.existsByUsernameAndPassword(request.getUsername(), request.getPassword());

        if (result) {
            return ResponseEntity.ok().body("Success");
        } else {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }
}
