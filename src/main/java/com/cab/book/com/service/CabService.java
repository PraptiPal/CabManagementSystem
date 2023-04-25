package com.cab.book.com.service;

import java.util.HashMap;
import java.util.List;

import com.cab.book.com.payload.CabDto;

public interface CabService {

	CabDto postCab(CabDto cabDto);
	
	CabDto updateCab(CabDto cabDto,Integer cabId);
	
	List<CabDto> listAllCabs();
	
	void deleteCab(Integer cabId);
	
	HashMap<String,Integer> viewAllCabTypes();
}
