package com.example.demo.exception;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

	    @ExceptionHandler(ProductNotFoundException.class)
	    public ResponseEntity<Map<String, Object>> handleProductNotFoundException(
	            ProductNotFoundException ex) {

	        Map<String, Object> response = new HashMap<>();
	        response.put("timestamp", LocalDateTime.now());
	        response.put("status", HttpStatus.NOT_FOUND.value());
	        response.put("error", "Product Not Found");
	        response.put("message", ex.getMessage());

	        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
	    }
	 
	    @ExceptionHandler(Exception.class)
	    public ResponseEntity<Map<String, Object>> handleGlobalException(
	            Exception ex) {

	        Map<String, Object> response = new HashMap<>();
	        response.put("timestamp", LocalDateTime.now());
	        response.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
	        response.put("error", "Internal Server Error");
	        response.put("message", ex.getMessage());

	        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	 
}
