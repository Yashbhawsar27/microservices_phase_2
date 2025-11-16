package com.java.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * Config Server Application - Centralized Configuration Management
 * 
 * This service provides centralized configuration management for all microservices.
 * It can serve configurations from local files or Git repository.
 * 
 * Key Features:
 * - Centralized Configuration: All service configs in one place
 * - Environment Management: Different configs for different environments
 * - Version Control: Git-based configuration versioning
 * - Dynamic Updates: Runtime configuration updates
 * - Security: Encrypted configuration values support
 * 
 * Configuration Sources:
 * - Local: classpath:/config-files/ (Default)
 * - Git: GitHub repository (Switch profile to 'git')
 * 
 * Port: 8888 (Standard Config Server port)
 * URL: http://localhost:8888
 * 
 * @author Learning Project
 * @version 1.0
 */
@SpringBootApplication  // Enables Spring Boot auto-configuration
@EnableConfigServer    // Enables this application as Config Server
@EnableEurekaClient   // Register this service with Eureka
public class ConfigServerApplication {

	/**
	 * Main method to start the Config Server application
	 * 
	 * @param args Command line arguments
	 */
	public static void main(String[] args) {
		// Start the Spring Boot application
		SpringApplication.run(ConfigServerApplication.class, args);
		
		// Log startup message
		System.out.println("===========================================");
		System.out.println("⚙️  Config Server Started Successfully!");
		System.out.println("📍 Config Server: http://localhost:8888");
		System.out.println("📁 Serving configs from local files");
		System.out.println("🔄 Switch to 'git' profile for GitHub configs");
		System.out.println("===========================================");
	}
}