package com.khus.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan("com.khus")
@SpringBootApplication
public class WeatherServiceProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(WeatherServiceProjectApplication.class, args);
	}

}
