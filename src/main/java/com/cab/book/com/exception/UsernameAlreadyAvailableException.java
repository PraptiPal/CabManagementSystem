package com.cab.book.com.exception;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@SuppressWarnings("serial")
public class UsernameAlreadyAvailableException extends RuntimeException{
	
		private String message;

		public UsernameAlreadyAvailableException(String message) {
			super();
			this.message = message;
		}
}
