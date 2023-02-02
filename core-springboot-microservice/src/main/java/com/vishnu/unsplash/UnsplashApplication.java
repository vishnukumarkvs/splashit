package com.vishnu.unsplash;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
@ComponentScan
public class UnsplashApplication {

	public static void main(String[] args) {
		SpringApplication.run(UnsplashApplication.class, args);
	}

}
