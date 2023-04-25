package com.cab.book.com.util;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import com.cab.book.com.entity.BookCab;
import com.cab.book.com.entity.Cab;
import com.cab.book.com.entity.Driver;
import com.cab.book.com.entity.User;
import com.cab.book.com.payload.BookCabDto;
import com.cab.book.com.payload.CabDto;
import com.cab.book.com.payload.DriverDto;
import com.cab.book.com.payload.UserDto;


@Component
public class Mapper {

	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}
	

	public User dtoToEntity(UserDto userDto ) {
		return modelMapper().map(userDto, User.class);
	}
	
	public UserDto entityToDto(User user) {
		return modelMapper().map(user, UserDto.class);
	}
	
	public Cab dtoToCab(CabDto cabDto ) {
		return modelMapper().map(cabDto, Cab.class);
	}
	
	public CabDto CabToDto(Cab cab) {
		return modelMapper().map(cab, CabDto.class);
	}
	
	public DriverDto driverToDto(Driver driver) {
		return modelMapper().map(driver, DriverDto.class);
	}
	
	public Driver dtoToDriver(DriverDto driverDto) {
		return modelMapper().map(driverDto, Driver.class);
	}
	
	public BookCab dtoToBookCab(BookCabDto dtoBookCab) {
		return modelMapper().map(dtoBookCab, BookCab.class);
	}
	
	public BookCabDto bookCabToDto(BookCab bookCab) {
		return modelMapper().map(bookCab, BookCabDto.class);
	}
	
}
