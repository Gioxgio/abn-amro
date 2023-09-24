package com.abnamro.utils;

import lombok.val;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.context.MessageSource;

import java.util.Locale;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

class MessageHelperTest extends UnitTestBase {

    @InjectMocks
    private MessageHelper unitToTest;
    @Mock
    private MessageSource messageSource;

    @Test
    void getMessage_success() {

        val key = "key";
        val args = new String[]{ "arg1", "arg2" };
        val message = "message";

        when(messageSource.getMessage(key, args, Locale.ENGLISH)).thenReturn(message);

        val response = unitToTest.getMessage(key, (Object[]) args);

        verify(messageSource).getMessage(key, args, Locale.ENGLISH);

        assertEquals(message, response);

        verifyNoMoreInteractions(messageSource);
    }
}