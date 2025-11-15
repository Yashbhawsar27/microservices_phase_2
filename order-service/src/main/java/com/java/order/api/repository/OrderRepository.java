package com.java.order.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.java.order.api.entity.Order;

public interface OrderRepository extends JpaRepository<Order, Integer> {

}
