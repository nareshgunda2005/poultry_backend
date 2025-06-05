package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class Poultry1Application {

    public static void main(String[] args) {
        SpringApplication.run(Poultry1Application.class, args);
    }
}

@Configuration
class WebConfig implements WebMvcConfigurer {

      @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // Map /Uploads/** to the Uploads/ directory in the project root
        registry
            .addResourceHandler("/Uploads/**")
            .addResourceLocations("file:Uploads/");
    }
}