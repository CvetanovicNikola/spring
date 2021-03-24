package com.example.demo;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;


//@ComponentScan(basePackages = {"security", "com.example.demo.student"})
@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}
	
//	@Bean
//	ConnectionFactoryInitializer initializer(@Qualifier("connectionFactory") ConnectionFactory connectionFactory) {
//	  ConnectionFactoryInitializer initializer = new ConnectionFactoryInitializer();
//	  initializer.setConnectionFactory(connectionFactory);
//	  ResourceDatabasePopulator resource =
//	      new ResourceDatabasePopulator(new ClassPathResource("schema.sql"));
//	  initializer.setDatabasePopulator(resource);
//	  return initializer;
//	}
}
