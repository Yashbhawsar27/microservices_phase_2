package com.java.gateway;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

/**
 * Fallback Controller - Circuit Breaker Fallback Endpoints
 * 
 * This controller provides fallback responses when downstream services
 * are unavailable or experiencing issues. It's part of the resilience
 * pattern implementation using Circuit Breaker.
 * 
 * @author Learning Project
 * @version 1.0
 */
@RestController
public class FallbackController {

    private static final Logger logger = LoggerFactory.getLogger(FallbackController.class);

    @GetMapping("/fallback/order")
    public ResponseEntity<Map<String, Object>> orderFallback() {
        logger.warn("Order service fallback activated");
        
        Map<String, Object> response = new HashMap<>();
        response.put("service", "order-service");
        response.put("status", "unavailable");
        response.put("message", "Order service is currently unavailable. Please try again later.");
        response.put("timestamp", LocalDateTime.now());
        
        return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(response);
    }

    @GetMapping("/fallback/payment")
    public ResponseEntity<Map<String, Object>> paymentFallback() {
        logger.warn("Payment service fallback activated");
        
        Map<String, Object> response = new HashMap<>();
        response.put("service", "payment-service");
        response.put("status", "unavailable");
        response.put("message", "Payment service is currently unavailable. Please try again later.");
        response.put("timestamp", LocalDateTime.now());
        
        return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(response);
    }
}