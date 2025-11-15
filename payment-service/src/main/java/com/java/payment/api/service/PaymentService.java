package com.java.payment.api.service;

import java.util.Random;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.java.payment.api.entity.Payment;
import com.java.payment.api.repository.PaymentRepository;

@Service
public class PaymentService {

	@Autowired
	private PaymentRepository repository;

	public Payment doPayment(Payment payment) {
		payment.setStatus(paymentProcesssing());
		payment.setTransactionId(UUID.randomUUID().toString());
		return repository.save(payment);
	}

	public String paymentProcesssing() {
		return new Random().nextBoolean() ? "success" : "False";
	}

}
