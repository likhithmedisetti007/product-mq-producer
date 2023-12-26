package com.likhith.producer.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.likhith.producer.document.Product;

public interface ProductRepository extends MongoRepository<Product, String> {

	Optional<Product> findByName(String name);

}