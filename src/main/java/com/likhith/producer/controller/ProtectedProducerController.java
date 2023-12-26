package com.likhith.producer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.likhith.producer.document.Product;
import com.likhith.producer.dto.ProductResponse;
import com.likhith.producer.service.ProducerService;

@RestController
@RequestMapping("/protected")
public class ProtectedProducerController {

	@Autowired
	ProducerService producerService;

	@PostMapping("/product/addProduct")
	public ResponseEntity<ProductResponse> addProduct(@RequestBody Product product) {

		String message = producerService.addProduct(product);

		return ResponseEntity.ok().body(new ProductResponse(message));
	}

}