package com.cab.book.com.payload;

import java.util.HashSet;
import java.util.Set;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import com.cab.book.com.entity.Cab;
import com.cab.book.com.entity.Role;
import com.cab.book.com.util.ContactNumberConstraint;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DriverDto {

	private Integer driverId;


	@NotEmpty(message = "Please enter a name")
	@Size(min=3, message = "Name should be atleast 3 characters")
    @Size(max=12, message = "Name should not be greater than 12 characters")
	private String driverName;
	
	@ContactNumberConstraint
	private Long contactNumber;
	
	private Integer rating;
	
	private boolean occupied;
	
	private String password;
	
	private Cab cab;
	
	private Set<Role> roles = new HashSet<>();;
}

