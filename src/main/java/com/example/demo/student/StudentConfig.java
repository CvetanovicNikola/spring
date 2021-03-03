package com.example.demo.student;

import java.time.LocalDate;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class StudentConfig {
	@Bean
	CommandLineRunner commandLineRunner(StudnetRepository repository) {
		return args -> {
			
			var mica = new Student(
					"Mica Vignjevic",
					"mica@gmail.com",
					LocalDate.of(1983, 04, 07)
					
			
			);
			
			var cale = new Student(
					"Cale Cakic",
					"cale@gmail.com",
					LocalDate.of(1983, 05, 07)
					
			
			);
			
			repository.saveAll(List.of(mica, cale));
		};
		
	}
}
//{
//	  "name":"Milos Milosevic",
//	  "email":"bransa@g.com",
//	  "dob":"1985-4-1"
//	}
