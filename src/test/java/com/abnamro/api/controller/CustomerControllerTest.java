package com.bank.api.controller;

import com.bank.api.request.CustomerLogonRequest;
import com.bank.api.request.CustomerRegisterRequest;
import com.bank.data.entity.Customer;
import com.bank.data.repository.AccountRepository;
import com.bank.data.repository.CustomerRepository;
import com.bank.mapper.AccountMapper;
import com.bank.mapper.CustomerMapper;
import com.bank.utils.UnitTestBase;
import com.bank.utils.validator.CustomerRequestValidator;
import com.bank.utils.validator.ValidationException;
import lombok.val;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

class CustomerControllerTest extends UnitTestBase {

    @InjectMocks
    private CustomerController unitToTest;

    @Mock
    private AccountMapper accountMapper;
    @Mock
    private AccountRepository accountRepository;
    @Mock
    private CustomerMapper customerMapper;
    @Mock
    private CustomerRequestValidator customerRequestValidator;
    @Mock
    private CustomerRepository customerRepository;

    @Test
    void register_invalid() {

        doThrow(new ValidationException("")).when(customerRequestValidator).validate(any());

        assertThrows(ValidationException.class, () -> unitToTest.register(null));

        verify(customerRequestValidator).validate(null);

        verifyNoMoreInteractions(customerRequestValidator);
        verifyNoInteractions(customerMapper);
        verifyNoInteractions(customerRepository);
        verifyNoInteractions(accountMapper);
        verifyNoInteractions(accountRepository);
    }

    @Test
    void register_valid() {

        val request = new CustomerRegisterRequest(null, null, null, null, null, null, "");

        val customerEntity = Customer.builder().id(1).build();
        when(customerRepository.saveAndFlush(any())).thenReturn(customerEntity);

        val response = unitToTest.register(request);

        assertEquals(HttpStatus.OK, response.getStatusCode());

        verify(customerRequestValidator).validate(request);
        verify(customerMapper).fromRegistrateRequest(any());
        verify(customerRepository).saveAndFlush(any());
        verify(accountMapper).fromScratch(anyInt(), any());
        verify(accountRepository).saveAndFlush(any());

        verifyNoMoreInteractions(customerRequestValidator);
        verifyNoMoreInteractions(customerMapper);
        verifyNoMoreInteractions(customerRepository);
        verifyNoMoreInteractions(accountMapper);
        verifyNoMoreInteractions(accountRepository);
    }

    @Test
    void logon_invalidRequest() {

        val request = new CustomerLogonRequest("", "");

        doThrow(new ValidationException("")).when(customerRequestValidator).validate(request);

        assertThrows(ValidationException.class, () -> unitToTest.logon(request));

        verify(customerRequestValidator).validate(request);

        verifyNoMoreInteractions(customerRequestValidator);
        verifyNoInteractions(customerRepository);
        verifyNoInteractions(accountMapper);
        verifyNoInteractions(accountRepository);
        verifyNoInteractions(customerMapper);
    }

    @Test
    void logon_invalidCredentials() {

        val request = new CustomerLogonRequest("", "");

        when(customerRepository.existsByUsernameAndPassword(any(), any())).thenReturn(false);

        val response = unitToTest.logon(request);

        assertEquals(HttpStatus.UNAUTHORIZED, response.getStatusCode());

        verify(customerRequestValidator).validate(request);
        verify(customerRepository).existsByUsernameAndPassword(any(), any());

        verifyNoMoreInteractions(customerRequestValidator);
        verifyNoMoreInteractions(customerRepository);
        verifyNoInteractions(accountMapper);
        verifyNoInteractions(accountRepository);
        verifyNoInteractions(customerMapper);
    }

    @Test
    void logon_valid() {

        val request = new CustomerLogonRequest("", "");

        when(customerRepository.existsByUsernameAndPassword(any(), any())).thenReturn(true);

        val response = unitToTest.logon(request);

        assertEquals(HttpStatus.OK, response.getStatusCode());

        verify(customerRequestValidator).validate(request);
        verify(customerRepository).existsByUsernameAndPassword(any(), any());

        verifyNoMoreInteractions(customerRequestValidator);
        verifyNoMoreInteractions(customerRepository);
        verifyNoInteractions(accountMapper);
        verifyNoInteractions(accountRepository);
        verifyNoInteractions(customerMapper);
    }
}
