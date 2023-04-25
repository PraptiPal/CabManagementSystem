package com.cab.book.com.entity;

import javax.persistence.Embeddable;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.cab.book.com.util.ContactNumberConstraint;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Embeddable
public class Address {

	private Integer houseNumber;
	
	private String streetName;
	
	//@ContactNumberConstraint
	private Integer pincode;
	
	private String landmark;
	
}
