package com.likhith.producer.service;

import java.util.List;

import org.springframework.amqp.AmqpException;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.likhith.producer.document.Product;
import com.likhith.producer.dto.ProductResponse;

@Service
public interface ProducerService {

	List<ProductResponse> getAllProducts();

	String selectProduct(String id) throws JsonProcessingException, AmqpException;

	String addProduct(Product product);

}