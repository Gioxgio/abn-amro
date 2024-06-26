package com.bank.api;

import com.bank.api.response.ErrorResponse;
import com.bank.utils.UnitTestBase;
import com.bank.utils.validator.ValidationException;
import lombok.val;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class RestExceptionHandlerTest extends UnitTestBase {

    @InjectMocks
    private RestExceptionHandler unitToTest;

    @ParameterizedTest
    @MethodSource("allPossibleStrings")
    void handleValidationException_success(String message) {

        val response = unitToTest.handleValidationException(new ValidationException(message));

        val body = response.getBody();
        assertInstanceOf(ErrorResponse.class, body);

        val errorResponse = ((ErrorResponse) body);
        assertNotNull(errorResponse);
        assertEquals(message, errorResponse.getMessage());
    }
}