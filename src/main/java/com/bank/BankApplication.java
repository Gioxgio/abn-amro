package com.bank;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;


@EnableJpaAuditing
@Slf4j
@SpringBootApplication
public class BankApplication {

    public static void main(String[] args) {

        SpringApplication.run(BankApplication.class, args);

        log.info("Service started...");
    }
}
