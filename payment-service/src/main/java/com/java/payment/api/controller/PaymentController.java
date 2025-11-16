package com.java.payment.api.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.java.payment.api.entity.Payment;
import com.java.payment.api.service.PaymentService;

import java.util.List;

/**
 * Payment Controller - REST API endpoints for Payment Processing
 * 
 * This controller handles all HTTP requests related to payment operations.
 * It provides secure payment processing with proper error handling and logging.
 * 
 * Key Features:
 * - Payment Processing: Handles payment transactions securely
 * - Payment Retrieval: Fetches payment history and details
 * - Distributed Tracing: All requests are traced with Zipkin
 * - Structured Logging: Logs include trace/span IDs for correlation
 * - Error Handling: Proper HTTP status codes and error responses
 * - Security: Input validation and secure payment processing
 * 
 * API Endpoints:
 * - POST /payment/doPayment - Process payment transaction
 * - GET /payment/all - Retrieve all payments (admin only)
 * - GET /payment/{id} - Retrieve specific payment by ID
 * 
 * @author Learning Project
 * @version 1.0
 */
@RestController  // Marks this class as REST controller
@RequestMapping("/payment")  // Base URL path for all endpoints
public class PaymentController {

	// Logger for structured logging with trace correlation
	private static final Logger logger = LoggerFactory.getLogger(PaymentController.class);

	// Dependency injection for Payment Service
	@Autowired
	private PaymentService service;

	/**
	 * Process Payment Transaction
	 * 
	 * This endpoint processes payment transactions securely.
	 * It validates payment details and processes the transaction.
	 * 
	 * @param payment Payment details including amount, order ID, and payment method
	 * @return ResponseEntity with payment result and status
	 */
	@PostMapping("/doPayment")
	public ResponseEntity<Payment> doPayment(@RequestBody Payment payment) {
		try {
			// Log incoming payment request (without sensitive data)
			logger.info("Payment processing request received for order ID: {}, amount: {}", 
					payment.getOrderId(), payment.getAmount());
			
			// Validate payment details
			if (payment.getAmount() == null || payment.getAmount() <= 0) {
				logger.warn("Invalid payment amount: {}", payment.getAmount());
				return ResponseEntity.badRequest().build();
			}
			
			// Process payment through service layer
			Payment processedPayment = service.doPayment(payment);
			
			// Log successful payment processing
			logger.info("Payment processed successfully with ID: {}, status: {}", 
					processedPayment.getPaymentId(), processedPayment.getPaymentStatus());
			
			return ResponseEntity.ok(processedPayment);
			
		} catch (Exception e) {
			// Log error without exposing sensitive information
			logger.error("Payment processing failed for order ID: {}: {}", 
					payment.getOrderId(), e.getMessage(), e);
			
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	/**
	 * Get All Payments
	 * 
	 * Retrieves all payment records from the database.
	 * Typically used for admin dashboards and financial reporting.
	 * 
	 * @return ResponseEntity with list of all payments
	 */
	@GetMapping("/all")
	public ResponseEntity<List<Payment>> getAllPayments() {
		try {
			logger.info("Fetching all payments");
			List<Payment> payments = service.getAllPayments();
			logger.info("Retrieved {} payments", payments.size());
			return ResponseEntity.ok(payments);
		} catch (Exception e) {
			logger.error("Error retrieving payments: {}", e.getMessage(), e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	/**
	 * Get Payment by ID
	 * 
	 * Retrieves a specific payment by its unique identifier.
	 * Used for payment verification and transaction history.
	 * 
	 * @param id Payment ID to retrieve
	 * @return ResponseEntity with payment details or 404 if not found
	 */
	@GetMapping("/{id}")
	public ResponseEntity<Payment> getPaymentById(@PathVariable Long id) {
		try {
			logger.info("Fetching payment with ID: {}", id);
			Payment payment = service.getPaymentById(id);
			
			if (payment != null) {
				logger.info("Payment found with status: {}", payment.getPaymentStatus());
				return ResponseEntity.ok(payment);
			} else {
				logger.warn("Payment not found with ID: {}", id);
				return ResponseEntity.notFound().build();
			}
		} catch (Exception e) {
			logger.error("Error retrieving payment with ID {}: {}", id, e.getMessage(), e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
}
