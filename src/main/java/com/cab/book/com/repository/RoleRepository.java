package com.cab.book.com.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cab.book.com.entity.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role,Integer>{

	//Role findRoleByName(String name);
	
	//public void addRoles() ;
		
		    
	
}
