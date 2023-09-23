package com.abnamro.api.controller;

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

class CustomerControllerTest extends UnitTestBase {

    @InjectMocks
    private CustomerController unitToTest;
    @Mock
    private CustomerRegisterRequestValidator customerRegisterRequestValidator;

    @Test
    void register_invalid() {

        doThrow(new ValidationException("")).when(customerRegisterRequestValidator).validate(any());

        assertThrows(ValidationException.class, () -> unitToTest.register(null));
    }

    @Test
    void register_valid() {

        val response = unitToTest.register(null);

        assertEquals(HttpStatus.OK, response.getStatusCode());
    }
}
