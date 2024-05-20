package com.bank.config;

import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@ConfigurationProperties(prefix = "business-config")
@Component
@Getter
public class BusinessConfig {

    private final Set<String> allowedCountries = new HashSet<>();
}
