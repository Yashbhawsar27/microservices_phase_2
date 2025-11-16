package com.yash;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * Service Registry Application - Eureka Server
 * 
 * This is the main class for the Service Registry (Eureka Server).
 * It acts as a service discovery server where all microservices register themselves.
 * 
 * Key Features:
 * - Service Discovery: All microservices register here
 * - Health Monitoring: Tracks service health status
 * - Load Balancing: Provides service instances for load balancing
 * - Fault Tolerance: Handles service failures gracefully
 * 
 * Port: 8761 (Standard Eureka port)
 * URL: http://localhost:8761
 * 
 * @author Learning Project
 * @version 1.0
 */
@SpringBootApplication  // Enables Spring Boot auto-configuration
@EnableEurekaServer    // Enables this application as Eureka Server
public class ServiceRegistryApplication {

	/**
	 * Main method to start the Service Registry application
	 * 
	 * @param args Command line arguments
	 */
	public static void main(String[] args) {
		// Start the Spring Boot application
		SpringApplication.run(ServiceRegistryApplication.class, args);
		
		// Log startup message
		System.out.println("===========================================");
		System.out.println("🚀 Service Registry Started Successfully!");
		System.out.println("📍 Eureka Dashboard: http://localhost:8761");
		System.out.println("🔍 All microservices will register here");
		System.out.println("===========================================");
	}
}