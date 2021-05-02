package com.astro.SmiteSolver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan(basePackages = {"com.astro", "com.astro.smitebasic"})
@EntityScan(basePackages = {"com.astro", "com.astro.smitebasic"})
public class SmiteSolverApplication {

	public static void main(String[] args) {
		SpringApplication.run(SmiteSolverApplication.class, args);
	}

}
