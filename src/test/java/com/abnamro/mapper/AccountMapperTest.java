package com.bank.mapper;

import com.bank.utils.UnitTestBase;
import lombok.val;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class AccountMapperTest extends UnitTestBase {

    private final AccountMapper unitToTest = new AccountMapper();

    @Test
    void fromScratch_success() {

        val entity = unitToTest.fromScratch(123, "investment");

        assertNotNull(entity);
    }
}