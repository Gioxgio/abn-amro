package com.abnamro.utils;

import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

import java.util.Locale;

@Component
@RequiredArgsConstructor
public class MessageHelper {

    private final MessageSource messageSource;

    public String getMessage(final String key, final Object... args) {

        return messageSource.getMessage(key, args, Locale.ENGLISH);
    }
}
