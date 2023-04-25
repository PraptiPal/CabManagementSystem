package com.cab.book.com.exception;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@SuppressWarnings("serial")
public class DriverNotAvailableException extends RuntimeException{

	private String message;
	
	public DriverNotAvailableException(String message) {
		super();
		this.message = message;
	}
}
