package com.vishnu.unsplash;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.vishnu.unsplash"})
public class UnsplashApplication {

	public static void main(String[] args) {
		SpringApplication.run(UnsplashApplication.class, args);
	}

}
