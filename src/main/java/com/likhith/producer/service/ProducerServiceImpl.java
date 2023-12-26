package com.likhith.producer.service;

import java.util.List;

import org.springframework.amqp.AmqpException;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.likhith.producer.document.Product;
import com.likhith.producer.dto.ProductResponse;
import com.likhith.producer.exception.ValidationException;
import com.likhith.producer.mapper.ProducerMapper;
import com.likhith.producer.repository.ProductRepository;

@Component
public class ProducerServiceImpl implements ProducerService {

	@Autowired
	ProductRepository productRepository;

	@Autowired
	ProducerMapper producerMapper;

	@Autowired
	RabbitTemplate rabbitTemplate;

	@Override
	public List<ProductResponse> getAllProducts() {

		List<Product> productList = productRepository.findAll();

		if (CollectionUtils.isEmpty(productList)) {
			throw new ValidationException(HttpStatus.NOT_FOUND.value(), "No products found");
		}

		List<ProductResponse> productResponseList = producerMapper.mapToProductResponseListFromProductList(productList);

		return productResponseList;
	}

	@Override
	public String selectProduct(String id) throws JsonProcessingException, AmqpException {

		Product product = productRepository.findById(id)
				.orElseThrow(() -> new ValidationException(HttpStatus.NOT_FOUND.value(), "Product not available"));

		ObjectMapper objectMapper = new ObjectMapper();

		rabbitTemplate.convertAndSend("productQueue", objectMapper.writeValueAsString(product));

		return "Product selected";
	}

	@Override
	public String addProduct(Product product) {

		productRepository.findByName(product.getName()).ifPresent(t -> {
			throw new ValidationException(HttpStatus.CONFLICT.value(), "Product already available");
		});

		productRepository.save(product);

		return "Product added successfully";
	}

}