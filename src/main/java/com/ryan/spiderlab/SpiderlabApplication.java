package com.ryan.spiderlab;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class SpiderlabApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpiderlabApplication.class, args);
	}

}
