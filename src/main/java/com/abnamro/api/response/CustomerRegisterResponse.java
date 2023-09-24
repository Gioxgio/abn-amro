package com.abnamro.api.response;

import com.abnamro.data.entity.Account;
import com.abnamro.data.entity.Customer;
import lombok.AllArgsConstructor;
import lombok.Value;

@AllArgsConstructor
@Value
public class CustomerRegisterResponse {

    Account account;
    Customer customer;
}
