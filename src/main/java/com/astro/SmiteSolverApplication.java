package com.astro;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@ComponentScan(basePackages = {"com.astro"})
@EntityScan(basePackages = {"com.astro"})
@EnableJpaRepositories(basePackages = {"com.astro"})
public class SmiteSolverApplication {

	public static void main(String[] args) {
		SpringApplication.run(SmiteSolverApplication.class, args);
	}

}
