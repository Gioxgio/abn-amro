package com.bank.api.response;

import com.bank.data.entity.Account;
import com.bank.data.entity.Customer;
import lombok.AllArgsConstructor;
import lombok.Value;

@AllArgsConstructor
@Value
public class CustomerRegisterResponse {

    Account account;
    Customer customer;
}
