package com.reco;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class RecotApplication {

	public static void main(String[] args) {
		SpringApplication.run(RecotApplication.class, args);
	}

}
