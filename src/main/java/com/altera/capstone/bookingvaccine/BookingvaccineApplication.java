package com.altera.capstone.bookingvaccine;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@SpringBootApplication
public class BookingvaccineApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookingvaccineApplication.class, args);
	}

	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurerAdapter() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
//				registry.addMapping("/v1/auth/**").allowedOrigins("**");
				registry.addMapping("/v1/**").allowedOrigins("**");
			}
		};
	}
}