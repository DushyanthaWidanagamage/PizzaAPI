package com.pizza.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.pizza.model.Order;

@RestController
public class OrderController {

	@Autowired
	private KafkaTemplate<String, Order> kafkaTemplate;
	
	@PostMapping(
			  value = "/pizza/placeOrder", consumes = "application/json", produces = "application/json")
	public Order placeOrder(@RequestBody Order newOrder) {

		kafkaTemplate.send("OrderQueue", newOrder);
		return newOrder;

	}

}
