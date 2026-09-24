package com.order.kafka.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.order.kafka.event.EventResponse;

import tools.jackson.databind.ObjectMapper;

@Service
public class KafkaProducer {

	@Autowired
	KafkaTemplate<String, EventResponse> kafkaTemplate;

	public void sendMessage(String _topic, EventResponse eventResponse) {
		System.out.println("Sending messages to kafka .. ");
		kafkaTemplate.send(_topic,String.valueOf(eventResponse.getOrderId()), eventResponse);
	}
}
