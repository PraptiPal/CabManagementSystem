package com.cab.book.com.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cab.book.com.payload.BookCabDto;
import com.cab.book.com.payload.DriverDto;
import com.cab.book.com.service.DriverService;

@RestController
@RequestMapping("/api/v1/driver")
public class DriverController {

	@Autowired private DriverService driverService;
	
	@PostMapping("/addDriver")
	public ResponseEntity<DriverDto> addDriver(@Valid @RequestBody DriverDto DriverDto){
		return new ResponseEntity<>(driverService.addDriver(DriverDto),HttpStatus.CREATED);
	}
	
	@GetMapping("/listAllDrivers")
	public ResponseEntity<List<DriverDto>> getAllDrivers(){
		return new ResponseEntity<>(driverService.listAllDrivers(),HttpStatus.OK);
	}
	
	@GetMapping("/getById/{driverId}")
	public ResponseEntity<DriverDto> getDriverById(@PathVariable Integer driverId){
		return new ResponseEntity<>(driverService.listDriverById(driverId),HttpStatus.OK);
	}
	
	@PutMapping("/updateDriver/{driverId}")
	public ResponseEntity<DriverDto> updateDriver(@Valid @RequestBody DriverDto driverDto, @PathVariable Integer driverId){
		return new ResponseEntity<>(driverService.updateDriver(driverDto, driverId),HttpStatus.OK);
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping("/deleteDriver/{driverId}")
	public ResponseEntity<String> deleteDriver(@PathVariable Integer driverId){
		driverService.deleteDriver(driverId);
		return new ResponseEntity<>("Driver Deleted",HttpStatus.OK);
	}
	
	
	@DeleteMapping("/deleteDriver")
	public ResponseEntity<String> deleteAllDriver(){
		driverService.deleteAll();
		return new ResponseEntity<>("Driver Deleted",HttpStatus.OK);
	}
	
	@GetMapping("/getCurrentBooking/{driverId}")
	public ResponseEntity<List<String>> getAllBookingsByDriverId(@PathVariable Integer driverId){
		return new ResponseEntity<>(driverService.viewCurrentBooking(driverId),HttpStatus.OK);
	}
	
	
	@GetMapping("/getPastBookings/{driverId}")
	public ResponseEntity<List<List<String>>> getPastBookingsByDriver(@PathVariable Integer driverId){
		return new ResponseEntity<>(driverService.viewPastBookings(driverId),HttpStatus.OK);
	}
}
