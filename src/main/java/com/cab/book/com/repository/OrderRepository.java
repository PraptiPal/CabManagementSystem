package com.cab.book.com.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cab.book.com.entity.Order;

public interface OrderRepository extends JpaRepository<Order,Long>{
	Order findByPaypalOrderId(String paypalOrderId);
}
