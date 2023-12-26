package com.likhith.producer.controller;

import org.springframework.amqp.AmqpException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.likhith.producer.dto.ProductResponse;
import com.likhith.producer.service.ProducerService;

@RestController
@RequestMapping("/private")
public class PrivateProducerController {

	@Autowired
	ProducerService producerService;

	@GetMapping("/product/selectProduct/{id}")
	public ResponseEntity<ProductResponse> selectProduct(@PathVariable("id") String id)
			throws JsonProcessingException, AmqpException {

		String message = producerService.selectProduct(id);

		return ResponseEntity.ok().body(new ProductResponse(message));
	}

}