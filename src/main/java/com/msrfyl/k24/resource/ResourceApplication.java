package com.msrfyl.k24.resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ResourceApplication {

	public static void main(String[] args) {
		System.setProperty("user.timezone", "Asia/Jakarta");
		Logger logger = LoggerFactory.getLogger(ResourceApplication.class);
		logger.info("starting application");
		SpringApplication.run(ResourceApplication.class, args);
	}

}
