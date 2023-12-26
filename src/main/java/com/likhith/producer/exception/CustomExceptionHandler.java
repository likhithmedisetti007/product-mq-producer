package com.likhith.producer.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.likhith.producer.dto.ProductResponse;

@RestControllerAdvice
public class CustomExceptionHandler {

	@ExceptionHandler(TechnicalException.class)
	public ResponseEntity<ProductResponse> technicalException(TechnicalException exception) {
		ErrorMessage errorMessage = new ErrorMessage(exception.getStatusCode(), exception.getErrorMessage());
		return ResponseEntity.status(errorMessage.getStatusCode()).body(new ProductResponse(errorMessage));
	}

	@ExceptionHandler(ValidationException.class)
	public ResponseEntity<ProductResponse> validationException(ValidationException exception) {
		ErrorMessage errorMessage = new ErrorMessage(exception.getStatusCode(), exception.getErrorMessage());
		return ResponseEntity.status(errorMessage.getStatusCode()).body(new ProductResponse(errorMessage));
	}

	@ExceptionHandler(RuntimeException.class)
	public ResponseEntity<ProductResponse> runtimeException(RuntimeException exception) {
		ErrorMessage errorMessage = new ErrorMessage(HttpStatus.INTERNAL_SERVER_ERROR.value(),
				"Unexpected Error occured. Please try again after sometime");
		return ResponseEntity.status(errorMessage.getStatusCode()).body(new ProductResponse(errorMessage));
	}
}