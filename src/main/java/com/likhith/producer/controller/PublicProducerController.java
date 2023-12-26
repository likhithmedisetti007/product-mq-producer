package com.likhith.producer.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.likhith.producer.dto.ProductResponse;
import com.likhith.producer.service.ProducerService;

@RestController
@RequestMapping("/public")
public class PublicProducerController {

	@Autowired
	ProducerService producerService;

	@GetMapping("/product/getAllProducts")
	public ResponseEntity<List<ProductResponse>> getAllProducts() {
		return ResponseEntity.ok().body(producerService.getAllProducts());
	}

}