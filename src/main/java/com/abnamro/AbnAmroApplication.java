package com.abnamro;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;


@EnableJpaAuditing
@Slf4j
@SpringBootApplication
public class AbnAmroApplication {

    public static void main(String[] args) {

        SpringApplication.run(AbnAmroApplication.class, args);

        log.info("Service started...");
    }
}
