package com.cab.book.com.security;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.cab.book.com.entity.Driver;
import com.cab.book.com.entity.User;
import com.cab.book.com.repository.DriverRepository;
import com.cab.book.com.repository.UserRepository;


@Service
public class CustomUserDetailsService  implements UserDetailsService{

	@Autowired
	private UserRepository userRepository;
	
	@Autowired DriverRepository driverRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		
		Optional<User> userObject = userRepository.findByUsername(username);
		if(userObject.isEmpty()) {
			Optional<Driver> driverObject = driverRepository.findByName(username);
			Driver driver = driverObject.get();
			Set<SimpleGrantedAuthority> authorities = new HashSet<>();
	        driver.getRoles().forEach(role -> {
	            authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getRoleName()));
	        });
		return new org.springframework.security.core.userdetails.User(username,
				driver.getPassword(), authorities);				
		}
		
		User user = userObject.get();
		 Set<SimpleGrantedAuthority> authorities = new HashSet<>();
	        user.getRoles().forEach(role -> {
	            authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getRoleName()));
	        });
		return new org.springframework.security.core.userdetails.User(username,
				user.getPassword(), authorities);				
	}

}
