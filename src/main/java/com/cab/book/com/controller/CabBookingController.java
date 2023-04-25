package com.cab.book.com.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.cab.book.com.payload.BookCabDto;
import com.cab.book.com.service.BookCabService;

@RestController
@RequestMapping("/api/v1/booking")
public class CabBookingController {

	@Autowired private BookCabService bookCabService;
	
	@PostMapping("/bookCab/{userId}")
	public ResponseEntity<BookCabDto> bookCab(@Valid @RequestBody BookCabDto bookCabDto,@PathVariable Integer userId){
		return new ResponseEntity<>(bookCabService.bookCab(bookCabDto,userId),HttpStatus.OK);
	}
	
	@GetMapping("/viewAllBookings")
	public ResponseEntity<List<BookCabDto>> viewAllBookings(){
		return new ResponseEntity<>(bookCabService.viewAllBookedCabs(),HttpStatus.OK);
	}
	
	@DeleteMapping("/deleteBooking/{bookingId}")
	public ResponseEntity<String> cancelBooking(@PathVariable Integer bookingId){
		bookCabService.cancelBooking(bookingId);
		return new ResponseEntity<>("Booking cancelled",HttpStatus.OK);
	}
	
	@GetMapping("/tripStatus/{bookingId}/{rating}")
	public ResponseEntity<String> viewAllBookings(@PathVariable Integer bookingId, @PathVariable(required = false) Integer rating){
		bookCabService.tripStatus(bookingId,rating);
		return new ResponseEntity<>("Trip Completed Successfully",HttpStatus.OK);
	}
	
	@GetMapping("/viewMyBookings/{userId}")
	public ResponseEntity<List<BookCabDto>> viewMyBookings(@PathVariable Integer userId){
		return new ResponseEntity<>(bookCabService.viewAllBookingsByUser(userId),HttpStatus.OK);
	}
	
	/*
	@DeleteMapping("/deleteBookings/")
	public ResponseEntity<String> deleteAllDriver(){
		bookCabService.deleteBooking();
		return new ResponseEntity<>("Driver Deleted",HttpStatus.OK);
	}**/
}
