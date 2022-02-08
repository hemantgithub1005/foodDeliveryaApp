package com.learning.fooddeliveryapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class FooddeliveryappApplication {

	public static void main(String[] args) {

		ConfigurableApplicationContext applicationContext =SpringApplication.run(FooddeliveryappApplication.class, args);
	}

}
