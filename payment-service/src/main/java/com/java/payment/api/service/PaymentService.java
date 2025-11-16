package com.java.payment.api.service;

import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.java.payment.api.entity.Payment;
import com.java.payment.api.repository.PaymentRepository;

/**
 * Payment Service - Business Logic for Payment Processing
 * 
 * This service handles all payment-related business operations including:
 * - Payment processing and validation
 * - Transaction ID generation
 * - Payment status management
 * - Payment history retrieval
 * 
 * Key Features:
 * - Secure payment processing simulation
 * - Unique transaction ID generation
 * - Payment status tracking
 * - Comprehensive logging for audit trails
 * - Error handling and validation
 * 
 * @author Learning Project
 * @version 1.0
 */
@Service  // Marks this class as a Spring service component
public class PaymentService {

	// Logger for structured logging and audit trails
	private static final Logger logger = LoggerFactory.getLogger(PaymentService.class);

	// Dependency injection for Payment Repository
	@Autowired
	private PaymentRepository repository;

	/**
	 * Process Payment Transaction
	 * 
	 * This method processes a payment transaction by:
	 * 1. Validating payment details
	 * 2. Simulating payment processing
	 * 3. Generating unique transaction ID
	 * 4. Saving payment record to database
	 * 
	 * @param payment Payment object containing payment details
	 * @return Processed payment with transaction ID and status
	 */
	public Payment doPayment(Payment payment) {
		logger.info("Processing payment for order ID: {}, amount: {}", 
				payment.getOrderId(), payment.getAmount());
		
		// Simulate payment processing and set status
		String paymentStatus = processPaymentTransaction();
		payment.setPaymentStatus(paymentStatus);
		
		// Generate unique transaction ID for tracking
		String transactionId = UUID.randomUUID().toString();
		payment.setTransactionId(transactionId);
		
		logger.info("Payment processed with transaction ID: {}, status: {}", 
				transactionId, paymentStatus);
		
		// Save payment record to database
		return repository.save(payment);
	}

	/**
	 * Simulate Payment Processing
	 * 
	 * This method simulates real payment processing by randomly
	 * determining success or failure. In production, this would
	 * integrate with actual payment gateways like Stripe, PayPal, etc.
	 * 
	 * @return Payment status ("SUCCESS" or "FAILED")
	 */
	private String processPaymentTransaction() {
		// Simulate payment processing with 80% success rate
		boolean isSuccessful = new Random().nextInt(100) < 80;
		return isSuccessful ? "SUCCESS" : "FAILED";
	}

	/**
	 * Get All Payments
	 * 
	 * Retrieves all payment records from the database.
	 * Used for admin dashboards and financial reporting.
	 * 
	 * @return List of all payments
	 */
	public List<Payment> getAllPayments() {
		logger.info("Retrieving all payments from database");
		return repository.findAll();
	}

	/**
	 * Get Payment by ID
	 * 
	 * Retrieves a specific payment by its unique identifier.
	 * Used for payment verification and transaction lookup.
	 * 
	 * @param id Payment ID to retrieve
	 * @return Payment object or null if not found
	 */
	public Payment getPaymentById(Long id) {
		logger.info("Retrieving payment with ID: {}", id);
		Optional<Payment> payment = repository.findById(id);
		return payment.orElse(null);
	}
}
