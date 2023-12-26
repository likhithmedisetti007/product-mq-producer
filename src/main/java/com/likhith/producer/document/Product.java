package com.likhith.producer.document;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Document(collection = "product")
@Data
public class Product {

	private String id;
	private String name;
	private String description;
	private Double price;

}