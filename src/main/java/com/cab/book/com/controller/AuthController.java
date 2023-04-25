package com.cab.book.com.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cab.book.com.payload.DriverDto;
import com.cab.book.com.payload.UserDto;
import com.cab.book.com.security.AuthRequest;
import com.cab.book.com.security.AuthResponse;
import com.cab.book.com.security.CustomUserDetailsService;
import com.cab.book.com.service.impl.DriverServiceImpl;
import com.cab.book.com.service.impl.UserServiceImpl;
import com.cab.book.com.util.JwtTokenGenerator;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {
	
	@Autowired
	private JwtTokenGenerator jwtTokenGenerator;
	
	@Autowired
	private CustomUserDetailsService myUserDetailsService;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired UserServiceImpl userServiceImpl;
	
	@Autowired DriverServiceImpl driverServiceImpl;
	
	@PostMapping("/login")
    public ResponseEntity<AuthResponse> generateToken(@RequestBody AuthRequest authRequest) throws AuthenticationException {

	 authenticationManager
		.authenticate(
		new UsernamePasswordAuthenticationToken(authRequest.getUsername(),
			authRequest.getPassword()));
	 	UserDetails userDetails = this.myUserDetailsService.loadUserByUsername(authRequest.getUsername());
	 
		String token = jwtTokenGenerator.generateToken(userDetails);
		
		return new ResponseEntity<AuthResponse>((new AuthResponse(token,"Success! Token mil gaya")),HttpStatus.OK);
		}
		
	@PostMapping("/user-registration")
	public ResponseEntity<String> registerNewUser(@Valid @RequestBody UserDto userDto){
		userServiceImpl.saveUser(userDto);
			return new ResponseEntity<>("User Successfully registered",HttpStatus.CREATED);
	}
	
	@PostMapping("/driver-registration")
	public ResponseEntity<String> registerNewDriver(@Valid @RequestBody DriverDto driverDto){
		driverServiceImpl.addDriver(driverDto);
			return new ResponseEntity<>("Driver Successfully registered",HttpStatus.CREATED);
	}
}
