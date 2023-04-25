package com.cab.book.com.service;

import java.util.List;

import com.cab.book.com.payload.BookCabDto;
import com.cab.book.com.payload.DriverDto;

public interface DriverService {
	
	DriverDto addDriver(DriverDto driverDto);
	
	DriverDto updateDriver(DriverDto driverDto, Integer driverId);
	
	List<DriverDto> listAllDrivers();
	
	DriverDto listDriverById(Integer driverId);
	
	void deleteDriver(Integer driverId);
	
	DriverDto getBestRatedDriver();
	
	Boolean getAvailableDriver();
	
	List<String> viewCurrentBooking(Integer driverId);
	
	List<List<String>> viewPastBookings(Integer driverId);
	
	void deleteAll();
}
