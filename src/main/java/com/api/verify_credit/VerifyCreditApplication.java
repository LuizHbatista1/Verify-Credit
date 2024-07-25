package com.api.verify_credit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@PropertySource("file:.env")
public class VerifyCreditApplication {

	public static void main(String[] args) {
		SpringApplication.run(VerifyCreditApplication.class, args);
	}

}
