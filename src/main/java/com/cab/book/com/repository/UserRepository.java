package com.cab.book.com.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cab.book.com.entity.User;

public interface UserRepository extends JpaRepository<User,Integer>{

	boolean existsByUsername(String username);
	Optional<User> findByUsername(String username);
}
