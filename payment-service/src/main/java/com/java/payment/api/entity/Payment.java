package com.java.payment.api.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * Payment Entity - Database model for Payment transactions
 * 
 * This entity represents a payment transaction in the system.
 * It stores all payment-related information including transaction details,
 * payment status, and audit information.
 * 
 * Database Table: PAYMENT_TB
 * 
 * Key Features:
 * - Auto-generated primary key
 * - Transaction tracking with unique transaction ID
 * - Payment status management
 * - Order association for transaction correlation
 * - Audit timestamps for tracking
 * - Input validation for data integrity
 * 
 * @author Learning Project
 * @version 1.0
 */
@Entity  // JPA entity annotation
@Table(name = "PAYMENT_TB")  // Database table name
@AllArgsConstructor  // Lombok: generates constructor with all fields
@NoArgsConstructor   // Lombok: generates no-args constructor
public class Payment {
	// Primary key with auto-generation
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long paymentId;
	
	// Payment status (SUCCESS, FAILED, PENDING)
	@NotNull(message = "Payment status cannot be null")
	@Column(name = "payment_status", nullable = false)
	private String paymentStatus;
	
	// Associated order ID for transaction correlation
	@NotNull(message = "Order ID cannot be null")
	@Column(name = "order_id", nullable = false)
	private String orderId;
	
	// Payment amount (should be positive)
	@Positive(message = "Payment amount must be positive")
	@Column(name = "amount", nullable = false)
	private Double amount;
	
	// Unique transaction ID for tracking
	@Column(name = "transaction_id", unique = true)
	private String transactionId;
	
	// Payment method (CARD, UPI, WALLET, etc.)
	@Column(name = "payment_mode")
	private String paymentMode;
	
	// Audit timestamp - when payment was created
	@Column(name = "created_at")
	private LocalDateTime createdAt;
	
	// Audit timestamp - when payment was last updated
	@Column(name = "updated_at")
	private LocalDateTime updatedAt;
	
	// Automatically set timestamps before persist and update
	@PrePersist
	protected void onCreate() {
		createdAt = LocalDateTime.now();
		updatedAt = LocalDateTime.now();
	}
	
	@PreUpdate
	protected void onUpdate() {
		updatedAt = LocalDateTime.now();
	}

	// Getter and Setter methods with proper naming
	
	public Long getPaymentId() {
		return paymentId;
	}

	public void setPaymentId(Long paymentId) {
		this.paymentId = paymentId;
	}

	public String getPaymentStatus() {
		return paymentStatus;
	}

	public void setPaymentStatus(String paymentStatus) {
		this.paymentStatus = paymentStatus;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public String getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}

	public String getPaymentMode() {
		return paymentMode;
	}

	public void setPaymentMode(String paymentMode) {
		this.paymentMode = paymentMode;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}

	/**
	 * Constructor for creating payment with essential fields
	 * 
	 * @param orderId Order ID associated with this payment
	 * @param amount Payment amount
	 * @param paymentMode Payment method used
	 */
	public Payment(String orderId, Double amount, String paymentMode) {
		this.orderId = orderId;
		this.amount = amount;
		this.paymentMode = paymentMode;
		this.paymentStatus = "PENDING";  // Default status
	}

	@Override
	public String toString() {
		return "Payment{" +
				"paymentId=" + paymentId +
				", paymentStatus='" + paymentStatus + '\'' +
				", orderId='" + orderId + '\'' +
				", amount=" + amount +
				", transactionId='" + transactionId + '\'' +
				", paymentMode='" + paymentMode + '\'' +
				", createdAt=" + createdAt +
				'}';
	}

}
