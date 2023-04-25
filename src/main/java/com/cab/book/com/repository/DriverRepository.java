package com.cab.book.com.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.cab.book.com.entity.BookCab;
import com.cab.book.com.entity.Driver;

public interface DriverRepository extends JpaRepository<Driver,Integer>{

//	Boolean findByAvailable(Boolean isOccupied);
	
//	@Query("Select bookings From BookCab bookings where bookings.driver_id = :driverId")
//	List<BookCab> viewYourBookings(@Param("driverId") Integer driverId);
	
	//Optional<Driver> findByEmail(String email);
	
	Optional<Driver> findByName(String driverName);
}
