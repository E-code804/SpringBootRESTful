package com.SpringBootEx.RESTfulApp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ResTfulAppApplication {

	// Run application: ./mvnw spring-boot:run
	// Build jar: java -jar target/gs-rest-service-0.1.0.jar
	public static void main(String[] args) {
		SpringApplication.run(ResTfulAppApplication.class, args);
	}

}
