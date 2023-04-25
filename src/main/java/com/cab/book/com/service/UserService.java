package com.cab.book.com.service;

import java.util.List;

import com.cab.book.com.payload.UserDto;

public interface UserService {

	UserDto saveUser(UserDto userDto);
	
	UserDto updateUser(UserDto userDto, Integer userId);
	
	List<UserDto> getAllUsers();
	
	void deleteUser(Integer userId);
	
	UserDto getOneUserDetails(Integer userId);
}
