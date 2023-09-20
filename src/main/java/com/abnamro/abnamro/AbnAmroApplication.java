package com.abnamro.abnamro;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4j
@SpringBootApplication
public class AbnAmroApplication {

	public static void main(String[] args) {
		SpringApplication.run(AbnAmroApplication.class, args);

		log.info("Service started...");
	}
}
