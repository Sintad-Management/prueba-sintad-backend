package com.sintad.management;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class PruebaSintadBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(PruebaSintadBackendApplication.class, args);
	}

}
