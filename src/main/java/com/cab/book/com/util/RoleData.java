package com.cab.book.com.util;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import com.cab.book.com.entity.Role;
import com.cab.book.com.repository.RoleRepository;


@Component
public class RoleData {

	@Autowired
	private RoleRepository roleRepository;
	
	@Bean
	public void addRoles() {
		
		 Role roleAdmin = new Role();
		    roleAdmin.setId(1);
		    roleAdmin.setRoleName("ROLE_ADMIN");
		    
		 Role roleDriver = new Role();
		    
		    roleDriver.setId(2);
		    roleDriver.setRoleName("ROLE_DRIVER");
		    
		 Role normalUser = new Role();
		 	normalUser.setId(3);
		 	normalUser.setRoleName("ROLE_USER");
		 	
		 List<Role> roles = List.of(roleAdmin,roleDriver,normalUser);
		    
		 roleRepository.saveAll(roles);
		    
	}
}
