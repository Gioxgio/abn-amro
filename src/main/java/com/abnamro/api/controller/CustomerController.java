package com.abnamro.api.controller;

import com.abnamro.api.request.CustomerRegisterRequest;
import com.abnamro.utils.validator.CommonValidator;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
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

    private final CommonValidator commonValidator;

    @PostMapping(value = "/register")
    @Operation(summary = "Allow to register a new customer")
    public ResponseEntity<Object> register(@Parameter(description = "Customer data", required = true)
                                           @RequestBody final CustomerRegisterRequest customer) {

        commonValidator.validate(customer);

        return ResponseEntity.ok().build();
    }
}
