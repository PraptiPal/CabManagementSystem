package com.cab.book.com.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.cab.book.com.entity.Role;
import com.cab.book.com.entity.User;
import com.cab.book.com.exception.ResourceNotFoundException;
import com.cab.book.com.exception.UsernameAlreadyAvailableException;
import com.cab.book.com.payload.UserDto;
import com.cab.book.com.repository.RoleRepository;
import com.cab.book.com.repository.UserRepository;
import com.cab.book.com.service.UserService;
import com.cab.book.com.util.Mapper;
import com.cab.book.com.util.RoleData;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private Mapper mapper;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired RoleData roleData;
	
	@Autowired RoleRepository roleRepository;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	public Optional<User> findByUsername(String username){
	       return userRepository.findByUsername(username);
	    }

	    public boolean exist(String username){
	       return userRepository.existsByUsername(username);
	    }
	    
		@Override
		public UserDto saveUser(UserDto userDto) {
			
			User user = mapper.dtoToEntity(userDto);
			List<User> userList = userRepository.findAll();
			
			userList.forEach(users -> {
				if(users.getUsername().equals(user.getUsername()))
					throw new UsernameAlreadyAvailableException("This username already exists");
			});
			
			Role role = this.roleRepository.findById(3).get();
			user.getRoles().add(role);
			
			user.setPassword(passwordEncoder.encode(user.getPassword()));
			return mapper.entityToDto(userRepository.save(user));
		}
	@Override
	public UserDto updateUser(UserDto userDto, Integer userId) {
		User userEntity = mapper.dtoToEntity(userDto);
		User user = userRepository.findById(userId).orElseThrow(
				() -> new ResourceNotFoundException("User","UserId",userId));
		user.setUsername(userEntity.getUsername());
		user.setEmailId(userEntity.getEmailId());
		user.setPassword(userEntity.getPassword());
		user.setFirstName(userEntity.getFirstName());
		user.setLastName(userEntity.getLastName());
		user.setAddress(userEntity.getAddress());
		userRepository.save(user);
		return mapper.entityToDto(user);
	}


	@Override
	public List<UserDto> getAllUsers() {
		List<UserDto> userList = new ArrayList<>();
		List<User> usersList = userRepository.findAll();
		usersList.forEach(user -> userList.add(mapper.entityToDto(user)));
		return userList;
	}

	@Override
	public void deleteUser(Integer userId) {
		User user = userRepository.findById(userId).orElseThrow();
		user.setRoles(null);
		userRepository.delete(user);
	}


	@Override
	public UserDto getOneUserDetails(Integer userId) {
		return mapper.entityToDto(userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User","UserId",userId)));
	}

}
