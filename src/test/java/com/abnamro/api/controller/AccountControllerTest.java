package com.abnamro.api.controller;

import com.abnamro.data.entity.Account;
import com.abnamro.data.repository.AccountRepository;
import com.abnamro.utils.UnitTestBase;
import lombok.val;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

class AccountControllerTest extends UnitTestBase {

    @InjectMocks
    private AccountController unitToTest;

    @Mock
    private AccountRepository accountRepository;

    @Test
    void overview_invalidAccountNumber() {

        when(accountRepository.getAccountByNumber(any())).thenReturn(Optional.empty());

        val response = unitToTest.overview(null);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());

        verify(accountRepository).getAccountByNumber(any());

        verifyNoMoreInteractions(accountRepository);
    }

    @Test
    void overview_valid() {

        when(accountRepository.getAccountByNumber(any())).thenReturn(Optional.of(new Account()));

        val response = unitToTest.overview(null);

        assertEquals(HttpStatus.OK, response.getStatusCode());

        verify(accountRepository).getAccountByNumber(any());

        verifyNoMoreInteractions(accountRepository);
    }
}