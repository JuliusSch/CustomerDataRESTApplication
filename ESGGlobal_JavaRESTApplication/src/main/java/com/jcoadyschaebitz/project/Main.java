package com.jcoadyschaebitz.project;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.jcoadyschaebitz.project.service.ApplicationService;

@SpringBootApplication
public class Main implements CommandLineRunner {
	
	public static void main(String[] args) {
		SpringApplication.run(Main.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		ApplicationService appService = new ApplicationService();
		appService.runApplication();
		System.exit(0); 
	}
}
