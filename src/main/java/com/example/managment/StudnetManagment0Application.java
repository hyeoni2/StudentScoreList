package com.example.managment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class StudnetManagment0Application {

	public static void main(String[] args) {
		SpringApplication.run(StudnetManagment0Application.class, args);
	}

}
