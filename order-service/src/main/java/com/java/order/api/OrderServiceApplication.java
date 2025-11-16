package com.java.order.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 * Order Service Application - Order Management Microservice
 * 
 * This service handles order management operations and integrates with payment service.
 * It demonstrates microservice communication, service discovery, and distributed tracing.
 * 
 * Key Features:
 * - Order Management: Create, read, update orders
 * - Payment Integration: Communicates with Payment Service
 * - Service Discovery: Registers with Eureka Server
 * - Load Balancing: Uses client-side load balancing
 * - Distributed Tracing: Integrated with Zipkin
 * - Database: Uses H2 in-memory database
 * - Configuration: Fetches config from Config Server
 * 
 * API Endpoints:
 * - POST /order/bookOrder - Create new order with payment
 * - GET /order/all - Get all orders
 * - GET /order/{id} - Get order by ID
 * 
 * Port: 8081
 * Database: H2 Console at http://localhost:8081/h2-console
 * 
 * @author Learning Project
 * @version 1.0
 */
@SpringBootApplication  // Enables Spring Boot auto-configuration
@EnableEurekaClient    // Register this service with Eureka Server
public class OrderServiceApplication {

	/**
	 * RestTemplate Bean Configuration
	 * 
	 * Creates a load-balanced RestTemplate for inter-service communication.
	 * The @LoadBalanced annotation enables client-side load balancing
	 * using service names instead of hardcoded URLs.
	 * 
	 * @return RestTemplate instance with load balancing capability
	 */
	@Bean
	@LoadBalanced  // Enable client-side load balancing
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}

	/**
	 * Main method to start the Order Service application
	 * 
	 * @param args Command line arguments
	 */
	public static void main(String[] args) {
		// Start the Spring Boot application
		SpringApplication.run(OrderServiceApplication.class, args);
		
		// Log startup message
		System.out.println("===========================================");
		System.out.println("🛒 Order Service Started Successfully!");
		System.out.println("📍 Order API: http://localhost:8081/order");
		System.out.println("🗄️  H2 Console: http://localhost:8081/h2-console");
		System.out.println("💳 Integrated with Payment Service");
		System.out.println("===========================================");
	}
}