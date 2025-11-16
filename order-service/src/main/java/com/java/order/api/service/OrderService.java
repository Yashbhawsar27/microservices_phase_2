package com.java.order.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.java.order.api.common.Payment;
import com.java.order.api.common.TransactionRequest;
import com.java.order.api.common.TransactionResponse;
import com.java.order.api.entity.Order;
import com.java.order.api.repository.OrderRepository;

import java.util.List;

/**
 * Order Service - Business Logic Layer for Order Management
 * 
 * This service contains the core business logic for order processing.
 * It handles order creation, payment integration, and data persistence.
 * 
 * Key Responsibilities:
 * - Order Processing: Validates and processes order requests
 * - Payment Integration: Communicates with Payment Service via REST
 * - Data Persistence: Saves orders to database
 * - Transaction Management: Handles distributed transactions
 * - Error Handling: Manages payment failures and rollbacks
 * - Logging: Structured logging with trace correlation
 * 
 * Microservice Communication:
 * - Uses RestTemplate with service discovery (Eureka)
 * - Load-balanced calls to payment-service
 * - Distributed tracing with Zipkin integration
 * 
 * @author Learning Project
 * @version 1.0
 */
@Service  // Marks this class as a Spring service component
public class OrderService {

	// Logger for structured logging with trace correlation
	private static final Logger logger = LoggerFactory.getLogger(OrderService.class);

	// Repository for database operations
	@Autowired
	private OrderRepository orderRepository;
	
	// RestTemplate for inter-service communication (load-balanced)
	@Autowired
	private RestTemplate restTemplate;

	/**
	 * Save Order with Payment Processing
	 * 
	 * This method demonstrates distributed transaction handling across microservices.
	 * It creates an order and processes payment through Payment Service.
	 * 
	 * Flow:
	 * 1. Extract order and payment details from request
	 * 2. Set payment details (order ID, amount)
	 * 3. Call Payment Service via REST API
	 * 4. Save order to database
	 * 5. Return transaction response with status
	 * 
	 * @param request TransactionRequest containing order and payment info
	 * @return TransactionResponse with order and payment status
	 */
	public TransactionResponse saveOrder(TransactionRequest request) {
		String responseString = "";
		
		// Extract order and payment from request
		Order order = request.getOrder();
		Payment payment = request.getPayment();
		
		// Set payment details from order
		payment.setOrderId(order.getId());
		payment.setAmount(order.getPrice());

		// Call Payment Service (demonstrates microservice communication)
		logger.info("Calling payment service for order: {}", order.getId());
		Payment paymentResponse = restTemplate.postForObject(
			"http://payment-service/payment/doPayment",  // Service name (Eureka resolves to actual URL)
			payment,
			Payment.class
		);

		// Save order to database (always save, even if payment fails)
		orderRepository.save(order);
		logger.info("Order saved to database: {}", order.getId());

		// Determine transaction status based on payment response
		responseString = paymentResponse != null && "success".equals(paymentResponse.getStatus()) 
				? "Payment processing successful and order placed"
				: "there is a failure in payment api , order added to cart";

		// Create and return transaction response
		return new TransactionResponse(
			order, 
			paymentResponse != null ? paymentResponse.getAmount() : 0.0, 
			paymentResponse != null ? paymentResponse.getTransactionId() : "FAILED",
			responseString
		);
	}
	
	/**
	 * Get All Orders
	 * 
	 * Retrieves all orders from the database.
	 * Used for admin dashboards and reporting.
	 * 
	 * @return List of all orders
	 */
	public List<Order> getAllOrders() {
		return orderRepository.findAll();
	}
	
	/**
	 * Get Order by ID
	 * 
	 * Retrieves a specific order by its unique identifier.
	 * Returns null if order is not found.
	 * 
	 * @param id Order ID to search for
	 * @return Order entity or null if not found
	 */
	public Order getOrderById(Long id) {
		return orderRepository.findById(id).orElse(null);
	}
}