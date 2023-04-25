package com.cab.book.com.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cab.book.com.entity.Cab;

public interface CabRepository extends JpaRepository<Cab,Integer> {

}
