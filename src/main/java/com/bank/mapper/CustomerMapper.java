package com.bank.mapper;

import com.bank.api.request.CustomerRegisterRequest;
import com.bank.data.entity.Customer;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Component;

@Component
public class CustomerMapper {

    public Customer fromRegistrateRequest(final CustomerRegisterRequest request) {

        return Customer.builder()
                       .name(request.getName())
                       .address(request.getAddress())
                       .idDocument(request.getIdDocument())
                       .dob(request.getDob())
                       .username(request.getUsername())
                       .password(generatePassword())
                       .country(request.getCountry())
                       .build();
    }

    private String generatePassword() {

        return RandomStringUtils.random(7, true, false);
    }
}
