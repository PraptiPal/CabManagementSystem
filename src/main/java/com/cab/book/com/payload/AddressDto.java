package com.cab.book.com.payload;

import javax.persistence.Embeddable;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.cab.book.com.entity.Address;

import lombok.Data;
import lombok.NoArgsConstructor;

	@Data
	@NoArgsConstructor
	public class AddressDto {

//		@NotEmpty
//		@Pattern(regexp = "^(0|[1-9][0-9]*)$")
		private Integer houseNumber;
		
		@NotBlank
		private String streetName;
		
		//@ContactNumberConstraint
		private Integer pincode;
		
		@NotBlank
		@Size(min = 5, max = 12)
		private String landmark;
		
	}

