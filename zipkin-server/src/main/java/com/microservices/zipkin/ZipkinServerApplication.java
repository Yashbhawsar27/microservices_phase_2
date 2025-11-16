package com.microservices.zipkin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Zipkin Server Application - Distributed Tracing Server
 * 
 * This service collects, stores, and visualizes distributed trace data from all microservices.
 * It helps in understanding request flows, latency analysis, and debugging distributed systems.
 * 
 * Key Features:
 * - Trace Collection: Receives trace data from all microservices
 * - Data Storage: Stores trace and span information
 * - Web UI: Provides visualization interface for traces
 * - Search & Filter: Find traces by service, operation, or time
 * - Dependency Graph: Shows service dependencies
 * - Performance Analysis: Identify bottlenecks and latency issues
 * 
 * How it works:
 * 1. Microservices send trace data to this server
 * 2. Zipkin stores and indexes the trace information
 * 3. Web UI allows viewing and analyzing traces
 * 4. Each request gets a unique Trace ID
 * 5. Each service call gets a unique Span ID
 * 
 * Port: 9411 (Standard Zipkin port)
 * URL: http://localhost:9411
 * 
 * @author Learning Project
 * @version 1.0
 */
@SpringBootApplication  // Enables Spring Boot auto-configuration and Zipkin server
public class ZipkinServerApplication {

    /**
     * Main method to start the Zipkin Server application
     * 
     * @param args Command line arguments
     */
    public static void main(String[] args) {
        // Start the Spring Boot application
        SpringApplication.run(ZipkinServerApplication.class, args);
        
        // Log startup message
        System.out.println("===========================================");
        System.out.println("🔍 Zipkin Server Started Successfully!");
        System.out.println("📍 Zipkin UI: http://localhost:9411");
        System.out.println("📊 Ready to collect distributed traces");
        System.out.println("🔎 Click 'Find Traces' to view request flows");
        System.out.println("===========================================");
    }
}