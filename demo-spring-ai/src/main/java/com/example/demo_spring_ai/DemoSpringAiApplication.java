package com.example.demo_spring_ai;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan(basePackages = { 
	"com.example.demo_spring_ai", 
    "com.example.controllers", 
	"com.example.services", 
	"com.example.entities", 
	"com.example.repositories", 
	"com.example.configs"
})
@EntityScan(basePackages = "com.example.entities")
@EnableJpaRepositories(basePackages = "com.example.repositories")
public class DemoSpringAiApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoSpringAiApplication.class, args);
	}

}
