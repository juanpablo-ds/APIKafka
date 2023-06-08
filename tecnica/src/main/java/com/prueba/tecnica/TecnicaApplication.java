package com.prueba.tecnica;

import com.prueba.tecnica.errorhandlers.CustomExceptionHandler;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class TecnicaApplication {

	public static void main(String[] args) {
		SpringApplication.run(TecnicaApplication.class, args);
	}

	@Bean
	public CustomExceptionHandler globalExceptionHandler() {
		return new CustomExceptionHandler();
	}

}
