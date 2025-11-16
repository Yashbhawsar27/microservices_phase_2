package com.java.order.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.java.order.api.common.TransactionRequest;
import com.java.order.api.common.TransactionResponse;
import com.java.order.api.entity.Order;
import com.java.order.api.service.OrderService;

import java.util.List;

/**
 * Order Controller - REST API endpoints for Order Management
 * 
 * This controller handles all HTTP requests related to order operations.
 * It provides endpoints for creating orders, retrieving orders, and managing order lifecycle.
 * 
 * Key Features:
 * - Order Creation: Creates orders and processes payments
 * - Order Retrieval: Fetches orders by ID or all orders
 * - Distributed Tracing: All requests are traced with Zipkin
 * - Structured Logging: Logs include trace/span IDs for correlation
 * - Error Handling: Proper HTTP status codes and error responses
 * 
 * API Endpoints:
 * - POST /order/bookOrder - Create order with payment processing
 * - GET /order/all - Retrieve all orders
 * - GET /order/{id} - Retrieve specific order by ID
 * 
 * @author Learning Project
 * @version 1.0
 */
@RestController  // Marks this class as REST controller
@RequestMapping("/order")  // Base URL path for all endpoints
public class OrderController {

	// Dependency injection for Order Service
	@Autowired
	private OrderService orderService;
	
	// Logger for structured logging with trace correlation
	private static final Logger logger = LoggerFactory.getLogger(OrderController.class);

	/**
	 * Create Order with Payment Processing
	 * 
	 * This endpoint creates a new order and processes payment through Payment Service.
	 * It demonstrates microservice communication and distributed transaction handling.
	 * 
	 * @param request TransactionRequest containing order and payment details
	 * @return TransactionResponse with order and payment status
	 */
	@PostMapping("/bookOrder")
	public TransactionResponse bookOrder(@RequestBody TransactionRequest request) {
		// Log incoming request with trace correlation
		logger.info("Order booking request received: {}", request.getOrder().getName());
		
		// Process order through service layer
		TransactionResponse response = orderService.saveOrder(request);
		
		// Log completion status
		logger.info("Order booking completed with status: {}", response.getMessage());
		
		return response;
	}
	
	/**
	 * Get All Orders
	 * 
	 * Retrieves all orders from the database.
	 * Useful for admin dashboards and order management.
	 * 
	 * @return List of all orders
	 */
	@GetMapping("/all")
	public List<Order> getAllOrders() {
		logger.info("Fetching all orders");
		return orderService.getAllOrders();
	}
	
	/**
	 * Get Order by ID
	 * 
	 * Retrieves a specific order by its unique identifier.
	 * Used for order details and status checking.
	 * 
	 * @param id Order ID to retrieve
	 * @return Order details or null if not found
	 */
	@GetMapping("/{id}")
	public Order getOrder(@PathVariable Long id) {
		logger.info("Fetching order with ID: {}", id);
		return orderService.getOrderById(id);
	}
}