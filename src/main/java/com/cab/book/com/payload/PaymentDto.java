package com.cab.book.com.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
public class PaymentDto {

	private Integer paymentId;
	
	private String currency;
	
	private String intent;
	private String method;
	private String description;
}
