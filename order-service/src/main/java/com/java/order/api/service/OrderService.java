package com.java.order.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.java.order.api.common.Payment;
import com.java.order.api.common.TransactionRequest;
import com.java.order.api.common.TransactionResponse;
import com.java.order.api.entity.Order;
import com.java.order.api.repository.OrderRepository;

@Service
public class OrderService {

	@Autowired
	private OrderRepository orderRepository;
	@Autowired
	private RestTemplate restTemplate;

	public TransactionResponse saveOrder(TransactionRequest request) {
		String responseString = "";
		Order order = request.getOrder();
		Payment payment = request.getPayment();
		payment.setOrderId(order.getId());
		payment.setAmount(order.getPrice());

		Payment paymentResponse = restTemplate.postForObject("http://payment-service/payment/doPayment", payment,
				Payment.class);

		orderRepository.save(order);

		responseString = paymentResponse != null && "success".equals(paymentResponse.getStatus()) 
				? "Payment processing successful and order placed"
				: "there is a failure in payment api , order added to cart";

		return new TransactionResponse(order, 
				paymentResponse != null ? paymentResponse.getAmount() : 0.0, 
				paymentResponse != null ? paymentResponse.getTransactionId() : "FAILED",
				responseString);

	}

}
