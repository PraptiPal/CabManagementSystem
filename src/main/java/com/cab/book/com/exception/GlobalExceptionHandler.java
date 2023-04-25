package com.cab.book.com.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import com.cab.book.com.payload.ApiResponse;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(DriverNotAvailableException.class)
	public ResponseEntity<ApiResponse> resourceNotFoundException(DriverNotAvailableException e){
		String message = e.getMessage();
		ApiResponse apiResponse = new ApiResponse(message);
		return new ResponseEntity<ApiResponse>(apiResponse, HttpStatus.NOT_FOUND);
	}	
	
//	@ExceptionHandler(UsernameAlreadyAvailableException.class)
//	public ResponseEntity<ApiResponse> usernameAlreadyExists(DriverNotAvailableException e){
//		String message = e.getMessage();
//		ApiResponse apiResponse = new ApiResponse(message);
//		return new ResponseEntity<ApiResponse>(apiResponse, HttpStatus.NOT_FOUND);
//	}
	
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ApiResponse> resourceNotFoundException(ResourceNotFoundException e){
		String message = e.getMessage();
		ApiResponse apiResponse = new ApiResponse(message);
		return new ResponseEntity<ApiResponse>(apiResponse, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(InvalidFormatException.class)
	public ResponseEntity<ApiResponse> handleInvalidFormatException(Exception e){
		String message = e.getMessage();
		ApiResponse apiResponse = new ApiResponse("Value is not valid");
		return new ResponseEntity<ApiResponse>(apiResponse, HttpStatus.NOT_FOUND);
		
	}
}
