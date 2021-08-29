package com.bh.tha;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.bh.tha.mappers")
public class ThaBhApplication {

	public static void main(String[] args) {
		SpringApplication.run(ThaBhApplication.class, args);
	}

}
