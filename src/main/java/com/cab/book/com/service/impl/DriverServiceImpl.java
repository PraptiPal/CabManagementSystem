package com.cab.book.com.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.cab.book.com.entity.BookCab;
import com.cab.book.com.entity.Driver;
import com.cab.book.com.entity.Role;
import com.cab.book.com.exception.ResourceNotFoundException;
import com.cab.book.com.payload.DriverDto;
import com.cab.book.com.repository.BookCabRepository;
import com.cab.book.com.repository.DriverRepository;
import com.cab.book.com.repository.RoleRepository;
import com.cab.book.com.service.DriverService;
import com.cab.book.com.util.Mapper;

@Service
public class DriverServiceImpl implements DriverService{

	@Autowired private DriverRepository driverRepository;
	
	@Autowired private BookCabRepository bookCabRepository;
	
	@Autowired private Mapper mapper;
	
	@Autowired private RoleRepository roleRepository;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@Override
	public DriverDto addDriver(DriverDto driverDto) {
		Role role = this.roleRepository.findById(2).get();
		driverDto.getRoles().add(role);		
		driverDto.setPassword(passwordEncoder.encode(driverDto.getPassword()));
		return mapper.driverToDto(driverRepository.save(mapper.dtoToDriver(driverDto)));
	}

	@Override
	public DriverDto updateDriver(DriverDto driverDto, Integer driverId) {
		Driver driver = driverRepository.findById(driverId).orElseThrow(
				() -> new ResourceNotFoundException("Driver","DriverId",driverId));
		if(driverDto.getContactNumber() != null)
		driver.setContactNumber(driverDto.getContactNumber());
		if(driverDto.getDriverName() != null)
		driver.setName(driverDto.getDriverName());
		return mapper.driverToDto(driverRepository.save(driver));
	}

	@Override
	public List<DriverDto> listAllDrivers() {
		List<DriverDto> driversList = new ArrayList<>();
		driverRepository.findAll().forEach(driver -> driversList.add(mapper.driverToDto(driver)));
		return driversList;
	}

	@Override
	public DriverDto listDriverById(Integer driverId) {
		return mapper.driverToDto(driverRepository.findById(driverId).orElseThrow(
				() -> new ResourceNotFoundException("Driver","DriverId",driverId)));
	}

	@Override
	public void deleteDriver(Integer driverId) {
		Driver driver = driverRepository.findById(driverId).orElseThrow(() -> new ResourceNotFoundException("Driver","DriverId",driverId));
		driverRepository.delete(driver);
	}

	@Override
	public DriverDto getBestRatedDriver() {
		
		return null;
	}

	@Override
	public Boolean getAvailableDriver() {
		List<Boolean> driversList = new ArrayList<>();
		driverRepository.findAll().forEach(driver -> driversList.add(driver.isOccupied()));
		System.out.println(driversList);
		if(!driversList.contains(false))
		return false;
		else
		return true;
	}
	
	
	@Override
	public List<String> viewCurrentBooking(Integer driverId){
		System.out.println(driverId);
		List<BookCab> bookingsDto = bookCabRepository.findAll();
		
		List<String> bookingDetails = new ArrayList<>();
		bookingsDto.forEach(booking ->{
			if(booking.getDriver().getDriverId() == driverId  && booking.getBookingStatus().equals("booked")) 
			{
				bookingDetails.add("Source");
				bookingDetails.add(booking.getFromLocation());
				bookingDetails.add("Destination");
				bookingDetails.add(booking.getToLocation());
				bookingDetails.add(String.valueOf(booking.getFromDate()));
				bookingDetails.add(String.valueOf(booking.getToDate()));
			}
		});

			return bookingDetails;	
	}

	@Override
	public void deleteAll() {
		driverRepository.deleteAll();	
	}

	@Override
	public List<List<String>> viewPastBookings(Integer driverId) {
		
		List<List<String>> pastBookings = new ArrayList<>();
		
		List<String> pastBooking = new ArrayList<>();
		List<BookCab> bookingsDto = bookCabRepository.findAll();
		
		bookingsDto.forEach(booking ->{
		if(booking.getDriver().getDriverId() == driverId  && booking.getBookingStatus().equals("trip completed")) //System.out.println("YES");
		{
			pastBooking.add(booking.getFromLocation());
			pastBooking.add(booking.getToLocation());
			pastBooking.add(String.valueOf(booking.getFromDate()));
			pastBooking.add(String.valueOf(booking.getToDate()));
			pastBookings.add(pastBooking);
		}
	});
	
	return pastBookings;	
	}	
}
