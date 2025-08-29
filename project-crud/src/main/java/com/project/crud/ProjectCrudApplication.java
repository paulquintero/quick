package com.project.crud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication(scanBasePackages = {"com.project.crud.*","com.quick.rest.*"})
public class ProjectCrudApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProjectCrudApplication.class, args);
	}

}
