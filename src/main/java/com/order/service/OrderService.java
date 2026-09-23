package com.order.service;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.order.entity.OrderEntity;
import com.order.kafka.event.EventResponse;
import com.order.kafka.service.KafkaProducer;
import com.order.repository.OrderRepository;
import com.order.response.OrderResponse;

@Service
public class OrderService {
	
	@Autowired
	OrderRepository orderRepository;
	
	
	@Autowired
	KafkaProducer producer;
	
	public ResponseEntity<OrderResponse> createOrder(OrderEntity entity) {
		
		OrderResponse orderResponse = new OrderResponse();
		
		if(entity.getCustomerId()!=null) {
			
			EventResponse eventResponse = new EventResponse();
			
			int orderidd = orderId();
			orderResponse.setOrderId(orderidd);
			orderResponse.setStatus("CREATED");
			
			eventResponse.setCustomerId(entity.getCustomerId());
			eventResponse.setEventId("EVT-"+orderidd);
			eventResponse.setEventType("ORDER_CREATED");
			eventResponse.setOrderId(orderidd);
			eventResponse.setAmount(entity.getAmount());
			eventResponse.setDeliveryAddress(entity.getDeliveryAddress());
			
			// before sending to kafka for reliable performance we will first add payment account  
			// other wise kafka will send unwanted messages in that ..
			
			producer.sendMessage("order-created", eventResponse);
			
			
			
			return new ResponseEntity<OrderResponse>(orderResponse, HttpStatus.CREATED);
			
		}
		throw new RuntimeException("Customer Id is null");
		
	}
	
	public int orderId() {
		Random r = new Random();
		int orderId = r.nextInt(9000) + 1000;
		return orderId;
		
	}
}
