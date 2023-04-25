package com.cab.book.com.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cab.book.com.entity.BookCab;
import com.cab.book.com.entity.Driver;
import com.cab.book.com.exception.DriverNotAvailableException;
import com.cab.book.com.payload.BookCabDto;
import com.cab.book.com.payload.DriverDto;
import com.cab.book.com.repository.BookCabRepository;
import com.cab.book.com.repository.DriverRepository;
import com.cab.book.com.service.BookCabService;
import com.cab.book.com.service.CabService;
import com.cab.book.com.service.DriverService;
import com.cab.book.com.util.Mapper;

@Service
public class BookCabServiceImpl implements BookCabService {

	@Autowired private BookCabRepository bookCabRepository;
	
	@Autowired private DriverRepository driverRepository;
	
	@Autowired private DriverService driverService;
	
	@Autowired private CabService cabService;
	
	@Autowired private Mapper mapper;
	
	@Override
	public BookCabDto bookCab(BookCabDto bookCabDto, Integer userId) {
		BookCab cab = mapper.dtoToBookCab(bookCabDto);
		cab.setUserId(userId);
		if(!driverService.getAvailableDriver()) {
			throw new DriverNotAvailableException("Drivers not available currently");
		}

		
	DriverDto driverr = driverService.listAllDrivers().stream().filter(driver -> 
						driver.isOccupied()== false).findFirst().orElseThrow();
				Driver driverObj =  mapper.dtoToDriver(driverr);
				cab.setDriver(driverObj);
			
				driverObj.setOccupied(true);
				driverRepository.save(driverObj);
				
				List<String> driverDetails = new ArrayList<>();
				driverDetails.add("Driver Information");
				cab.setDriverField(driverObj.getName()); driverDetails.add(driverObj.getName());
				driverDetails.add(String.valueOf(driverObj.getContactNumber()));
		
				
		Integer distance = cab.getDistance();
		Integer dprice=0;
		if(distance <= 10)
			 dprice = 100;
		else if(distance >=11 && distance <= 30)
			 dprice = 100 + (distance-10)*20;
		else if(distance >=31 && distance <= 50)
			 dprice = 500 + (distance-30)*30;
		else if(distance > 50) dprice = 1500;
	
		String cabType = cab.getCabType();
		HashMap<String,Integer> cabMap = cabService.viewAllCabTypes();
		
		Integer price = cabMap.get(cabType);
		System.out.print(price);		
		cab.setTotalCharge(price + dprice);
		cab.setBookingStatus("booked");
		
		BookCabDto bg = mapper.bookCabToDto(bookCabRepository.save(cab));
		return bg;
	}
	
	@Override
	public BookCabDto updateCabBooking(Integer bookingId, BookCabDto bookCabDto) {
		
		Optional<BookCab> cabBooking = bookCabRepository.findById(bookingId);
		BookCab cabBookingObj = cabBooking.get();
	
		Integer distance = bookCabDto.getDistance();
		Integer dprice=0;
		if(distance <= 10)
			 dprice = 100;
		else if(distance >=11 && distance <= 30)
			 dprice = 100 + (distance-10)*20;
		else if(distance >=31 && distance <= 50)
			 dprice = 500 + (distance-30)*30;
		else if(distance > 50) dprice = 1500;
	
		String cabType = bookCabDto.getCabType();
		HashMap<String,Integer> cabMap = cabService.viewAllCabTypes();
		
		Integer price = cabMap.get(cabType);
		System.out.print(price);		
		bookCabDto.setTotalCharge(price + dprice);
		bookCabDto.setBookingStatus("booked");
		return mapper.bookCabToDto(bookCabRepository.save(mapper.dtoToBookCab(bookCabDto)));
	}

	
	@Override
	public List<BookCabDto> viewAllBookingsByUser(Integer userId) {
		System.out.println("In here");
		List<BookCabDto> userBookings= new ArrayList<>(); 
		bookCabRepository.findAll().forEach(booking -> {
			mapper.bookCabToDto(booking);
			if(booking.getUserId() == userId) {
				System.out.println("In here loop");
				userBookings.add(mapper.bookCabToDto(booking));
			}
				}
				);
		return userBookings;
	}

	@Override
	public List<BookCabDto> viewAllBookedCabs() {
		List<BookCabDto> allBookings = new ArrayList<>();
		bookCabRepository.findAll().forEach(booking -> allBookings.add(mapper.bookCabToDto(booking)));;
		return allBookings;
	}

	@Override
	public void cancelBooking(Integer bookingId) {
		BookCab booking = bookCabRepository.findById(bookingId).orElseThrow();
		
		Driver driver = booking.getDriver(); //.setAvailable(false);
		driver.setOccupied(false);
		driverRepository.save(driver); //.setAvailable(true));
		booking.setBookingStatus("cancelled");
		bookCabRepository.save(booking);
		//bookCabRepository.delete(booking);
	}


	@Override
	public void tripStatus(Integer bookingId, Integer rating) {
		BookCab booking = bookCabRepository.findById(bookingId).orElseThrow();
		Driver driver = booking.getDriver(); 
		driver.setRating(rating);
		driver.setOccupied(false);
		driverRepository.save(driver);
		booking.setBookingStatus("trip completed");
		bookCabRepository.save(booking);
		
	}


	@Override
	public void deleteBooking() {
		bookCabRepository.deleteAll();
		
	}
}

