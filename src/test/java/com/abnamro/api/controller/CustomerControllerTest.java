package com.abnamro.api.controller;

import com.abnamro.data.entity.Customer;
import com.abnamro.data.repository.CustomerRepository;
import com.abnamro.mapper.CustomerMapper;
import com.abnamro.utils.UnitTestBase;
import com.abnamro.utils.validator.CustomerRegisterRequestValidator;
import com.abnamro.utils.validator.ValidationException;
import lombok.val;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

class CustomerControllerTest extends UnitTestBase {

    @InjectMocks
    private CustomerController unitToTest;

    @Mock
    private CustomerMapper customerMapper;
    @Mock
    private CustomerRegisterRequestValidator customerRegisterRequestValidator;
    @Mock
    private CustomerRepository customerRepository;

    @Test
    void register_invalid() {

        doThrow(new ValidationException("")).when(customerRegisterRequestValidator).validate(any());

        assertThrows(ValidationException.class, () -> unitToTest.register(null));

        verify(customerRegisterRequestValidator).validate(null);

        verifyNoMoreInteractions(customerRegisterRequestValidator);
        verifyNoInteractions(customerMapper);
        verifyNoInteractions(customerRepository);
    }

    @Test
    void register_valid() {

        val response = unitToTest.register(null);

        when(customerMapper.fromRegistrateRequest(any())).thenReturn(null);

        assertEquals(HttpStatus.OK, response.getStatusCode());

        verify(customerRegisterRequestValidator).validate(null);
        verify(customerMapper).fromRegistrateRequest(any());
        verify(customerRepository).saveAndFlush(any());

        verifyNoMoreInteractions(customerRegisterRequestValidator);
        verifyNoMoreInteractions(customerMapper);
        verifyNoMoreInteractions(customerRepository);
    }
}
