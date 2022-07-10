package com.altera.capstone.bookingvaccine.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;

@Configuration
@EnableSwagger2
@EnableWebMvc
public class SwaggerConfiguration {
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.OAS_30)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.altera.capstone.bookingvaccine.controller"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(apiInfo());
    }
    private ApiInfo apiInfo() {
        return new ApiInfo(
                "Capstone Project Spring Boot",
                "API for Booking Vaccine System.",
                "V1",
                "Terms of service",
                new Contact("powered by of backend team kelompok 6", "https://github.com/CapstoneProject-BookingVaccineSystem/bookingvaccine-backend.git", "aryandh.a.w@gmail.com"),
                "License of API", "API license URL", Collections.emptyList());
    }
}
