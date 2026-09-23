package com.order.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.order.entity.OrderEntity;
import com.order.response.OrderResponse;
import com.order.service.OrderService;

@RestController
public class OrderController {
	
	@Autowired
	OrderService orderService;
	
	@PostMapping("/createorder")
	public ResponseEntity<OrderResponse> createOrder(@RequestBody OrderEntity orderEntity) {
		return orderService.createOrder(orderEntity);
	}
}
