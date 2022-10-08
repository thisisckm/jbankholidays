package com.ck.bankholidays;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class BankholidaysApplication {

	public static void main(String[] args) {
		SpringApplication.run(BankholidaysApplication.class, args);
	}

}
