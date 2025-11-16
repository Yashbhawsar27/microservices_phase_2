package com.java.order.api.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * Order Entity - Database model for Order management
 * 
 * This entity represents an order in the e-commerce system.
 * It stores order details including product information, quantity,
 * pricing, and audit information.
 * 
 * Database Table: ORDER_TB
 * 
 * Key Features:
 * - Auto-generated primary key
 * - Product information storage
 * - Quantity and pricing management
 * - Audit timestamps for tracking
 * - Input validation for data integrity
 * - Order status tracking
 * 
 * @author Learning Project
 * @version 1.0
 */
@Entity  // JPA entity annotation
@Table(name = "ORDER_TB")  // Database table name
@NoArgsConstructor   // Lombok: generates no-args constructor
@AllArgsConstructor  // Lombok: generates constructor with all fields
public class Order {
	// Primary key with auto-generation
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	// Product/Order name (required field)
	@NotBlank(message = "Order name cannot be blank")
	@Column(name = "order_name", nullable = false, length = 255)
	private String name;
	
	// Quantity ordered (must be positive)
	@Positive(message = "Quantity must be positive")
	@Column(name = "quantity", nullable = false)
	private Integer qty;
	
	// Order price (must be positive)
	@Positive(message = "Price must be positive")
	@Column(name = "price", nullable = false)
	private Double price;
	
	// Order status (PENDING, CONFIRMED, SHIPPED, DELIVERED, CANCELLED)
	@Column(name = "order_status")
	private String orderStatus = "PENDING";
	
	// Customer information
	@Column(name = "customer_id")
	private String customerId;
	
	// Audit timestamp - when order was created
	@Column(name = "created_at")
	private LocalDateTime createdAt;
	
	// Audit timestamp - when order was last updated
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

	// Getter and Setter methods
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getQty() {
		return qty;
	}

	public void setQty(Integer qty) {
		this.qty = qty;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public String getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
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
	 * Calculate total order value
	 * 
	 * @return Total value (quantity * price)
	 */
	public Double getTotalValue() {
		return qty != null && price != null ? qty * price : 0.0;
	}

	@Override
	public String toString() {
		return "Order{" +
				"id=" + id +
				", name='" + name + '\'' +
				", qty=" + qty +
				", price=" + price +
				", orderStatus='" + orderStatus + '\'' +
				", customerId='" + customerId + '\'' +
				", createdAt=" + createdAt +
				'}';
	}

}
