package com.likhith.producer.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.likhith.producer.exception.ErrorMessage;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProductResponse {

	private String name;
	private String description;
	private Double price;

	private String message;
	private ErrorMessage error;

	public ProductResponse(String message) {
		super();
		this.message = message;
	}

	public ProductResponse(ErrorMessage error) {
		super();
		this.error = error;
	}

}