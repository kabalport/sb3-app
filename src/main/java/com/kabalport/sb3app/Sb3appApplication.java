package com.kabalport.sb3app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories
@SpringBootApplication
public class Sb3appApplication {

	public static void main(String[] args) {
		SpringApplication.run(Sb3appApplication.class, args);
	}

}
