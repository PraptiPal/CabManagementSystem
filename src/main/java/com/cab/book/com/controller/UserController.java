package com.cab.book.com.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cab.book.com.payload.ApiResponse;
import com.cab.book.com.payload.UserDto;
import com.cab.book.com.service.UserService;
import com.cab.book.com.service.impl.UserServiceImpl;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {

	@Autowired private UserService userService;
	
	@Autowired private UserServiceImpl userServiceImpl;
	
	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping("/addUser")
	public ResponseEntity<UserDto> addNewUser(@Valid @RequestBody UserDto userDto){
		return new ResponseEntity<>(userService.saveUser(userDto),HttpStatus.CREATED);
	}
	
	@PutMapping("/updateUser/{userId}")
	public ResponseEntity<UserDto> updateUser(@Valid @RequestBody UserDto userDto, @PathVariable Integer userId){
		return new ResponseEntity<>(userService.updateUser(userDto, userId),HttpStatus.OK);
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("/getAllUsers")
	public ResponseEntity<List<UserDto>> getAllUsers(){
		return new ResponseEntity<>(userService.getAllUsers(),HttpStatus.OK);
	}
	
	@GetMapping("/getSingleUser/{userId}")
	public ResponseEntity<UserDto> getSingleUser(@PathVariable Integer userId){
		userService.getOneUserDetails(userId);
		return new ResponseEntity<>(userService.getOneUserDetails(userId),HttpStatus.OK);
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping("/deleteUser/{userId}")
	public ResponseEntity<String> deleteUser(@PathVariable Integer userId){
		userService.deleteUser(userId);
		return new ResponseEntity<>("User Deleted",HttpStatus.OK);
	}
}
