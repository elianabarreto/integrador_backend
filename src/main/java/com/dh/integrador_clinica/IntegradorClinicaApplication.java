package com.dh.integrador_clinica;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.boot.autoconfigure.domain.EntityScan;
//import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
//@EntityScan("com.dh.integrador_clinica.entity")
//@EnableJpaRepositories("com.dh.integrador_clinica.repository")
public class IntegradorClinicaApplication {

	public static void main(String[] args) {
		SpringApplication.run(IntegradorClinicaApplication.class, args);

	}

}
