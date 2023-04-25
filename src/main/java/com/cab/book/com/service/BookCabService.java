package com.cab.book.com.service;

import java.util.List;

import com.cab.book.com.payload.BookCabDto;

public interface BookCabService {

	BookCabDto bookCab(BookCabDto bookCabDto, Integer userId);
	
	BookCabDto updateCabBooking(Integer bookingId, BookCabDto bookCabDto);

	List<BookCabDto> viewAllBookingsByUser(Integer userId);
	
	List<BookCabDto> viewAllBookedCabs();
	
	void cancelBooking(Integer bookingId);
	
	void tripStatus(Integer bookingId,Integer rating);
	
	void deleteBooking();
}
