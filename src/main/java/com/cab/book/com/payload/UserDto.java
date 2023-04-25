package com.cab.book.com.payload;

import java.util.HashSet;
import java.util.Set;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import com.cab.book.com.entity.Address;
import com.cab.book.com.entity.Role;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class UserDto {

	private Integer id;
	
	private String username;
	

	@NotEmpty(message = "Please enter a name")
	@Size(min=3, message = "Name should be atleast 3 characters")
    @Size(max=12, message = "Name should not be greater than 12 characters")
	private String firstName;
	
	@NotEmpty(message = "Please enter a name")
	@Size(min=3, message = "Name should be atleast 3 characters")
    @Size(max=12, message = "Name should not be greater than 12 characters")
	private String lastName;
	
	private String password;
	
	@Email
	private String emailId;
	
	private Set<Address> address;
	
	private Set<Role> roles = new HashSet<>();
}
