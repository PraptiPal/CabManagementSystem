package com.cab.book.com.payload;

import lombok.Data;

@Data
public class CabDto {
	
	private Integer id;
	
	private String cabNumber;
	
	private String cabType;
	
	private String cabPriceAccToType;
}
