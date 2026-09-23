package com.order.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "orders_kafka_table")
@Data
public class OrderEntity {
	
	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long customerId;

	    private String customerName;

	    private Long productId;

	    private String productName;

	    private Integer quantity;

	    private Double amount;

	    private String deliveryAddress;
	    
	    private int orderId;
	    
	    private String status;

}
