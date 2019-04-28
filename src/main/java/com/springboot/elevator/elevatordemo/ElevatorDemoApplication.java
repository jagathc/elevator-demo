package com.springboot.elevator.elevatordemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@PropertySource("classpath:application.properties")
public class ElevatorDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(ElevatorDemoApplication.class, args);
	}

}
