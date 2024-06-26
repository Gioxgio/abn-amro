package com.bank.mapper;

import com.bank.api.request.CustomerRegisterRequest;
import com.bank.utils.UnitTestBase;
import lombok.val;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class CustomerMapperTest extends UnitTestBase {

    private final CustomerMapper unitToTest = new CustomerMapper();

    @Test
    void fromRegisterRequest_success() {

        val request = new CustomerRegisterRequest("name", "address", LocalDate.now(), "idDocument", "username", "country", null);

        val entity = unitToTest.fromRegistrateRequest(request);

        assertNotNull(entity);
    }
}