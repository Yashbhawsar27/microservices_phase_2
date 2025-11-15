package com.java.gateway;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FallbackController {

    @GetMapping("/fallback/order")
    public String orderFallback() {
        return "Order service is currently unavailable. Please try again later.";
    }

    @GetMapping("/fallback/payment")
    public String paymentFallback() {
        return "Payment service is currently unavailable. Please try again later.";
    }
}