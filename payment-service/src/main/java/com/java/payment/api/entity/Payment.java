package com.java.payment.api.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "PAYMENT_TB")
@AllArgsConstructor
@NoArgsConstructor

public class Payment {
	@Id
	@GeneratedValue
	private int paymentId;
	private String status;
	private String orderId;
	private int amount;
	private String transactionId;

	public int getPaymentId() {
		return paymentId;
	}

	public void setPaymentId(int paymentId) {
		this.paymentId = paymentId;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public String getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public Payment() {

	}

	public Payment(int paymentId, String status, String orderId, int amount, String transactionId) {

		this.paymentId = paymentId;
		this.status = status;
		this.orderId = orderId;
		this.amount = amount;
		this.transactionId = transactionId;
	}

}
