package com.sheduler.sheduler;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
public class ShedulerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ShedulerApplication.class, args);
	}

}
