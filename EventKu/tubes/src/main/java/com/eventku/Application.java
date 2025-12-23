package com.eventku;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * SPRING BOOT APPLICATION
 * Main entry point untuk web application
 * Menggunakan Spring Boot framework untuk REST API
 */
@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
        System.out.println("\n╔════════════════════════════════════════════════════════╗");
        System.out.println("║                                                        ║");
        System.out.println("║   🚀 Event Ticket Booking System - RUNNING           ║");
        System.out.println("║                                                        ║");
        System.out.println("║   Backend API: http://localhost:8080/api              ║");
        System.out.println("║   Frontend: Open index.html in browser                ║");
        System.out.println("║                                                        ║");
        System.out.println("╚════════════════════════════════════════════════════════╝\n");
    }

    /**
     * CORS Configuration
     * Mengizinkan frontend mengakses backend API
     */
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedOrigins(
                            "http://localhost:3000",
                            "http://localhost:8080",
                            "http://127.0.0.1:5500",
                            "http://localhost:5500"
                        )
                        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                        .allowedHeaders("*")
                        .allowCredentials(true)
                        .maxAge(3600);
            }
        };
    }
}

