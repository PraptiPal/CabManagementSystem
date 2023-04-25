package com.cab.book.com.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cab.book.com.exception.ResourceNotFoundException;
import com.cab.book.com.entity.Cab;
import com.cab.book.com.payload.CabDto;
import com.cab.book.com.repository.CabRepository;
import com.cab.book.com.service.CabService;
import com.cab.book.com.util.Mapper;

@Service
public class CabServiceImpl implements CabService{

	@Autowired private CabRepository cabRepository;
	
	@Autowired private Mapper mapper;
	
	@Override
	public CabDto postCab(CabDto cabDto) {
		return mapper.CabToDto(cabRepository.save(mapper.dtoToCab(cabDto)));
	}

	@Override
	public CabDto updateCab(CabDto cabDto, Integer cabId) {
		Cab cab = cabRepository.findById(cabId).orElseThrow(
				()-> new ResourceNotFoundException("Cab", "cabId", cabId));
		if(cabDto.getCabPriceAccToType() != null)
		cab.setCabPriceAccToType(cabDto.getCabPriceAccToType());
		if(cabDto.getCabType()!=null)
		cab.setCabType(cabDto.getCabType());
		
		return mapper.CabToDto(cabRepository.save(cab));
	}

	@Override
	public List<CabDto> listAllCabs() {
		
		List<CabDto> cabList = new ArrayList<>();
		cabRepository.findAll().forEach(cab ->  cabList.add(mapper.CabToDto(cab)));
		return cabList;
	}

	@Override
	public void deleteCab(Integer cabId) {
		cabRepository.delete(cabRepository.findById(cabId).orElseThrow(
				() -> new ResourceNotFoundException("Cab","CabId",cabId)));
	}

	@Override
	public HashMap<String,Integer> viewAllCabTypes() {
		HashMap< String,Integer> cabTypeList = new HashMap<>();
		List<Cab> cabList = new ArrayList<>();
		cabRepository.findAll().forEach(cab ->  cabList.add(cab));
		for(Cab cab : cabList) {
			cabTypeList.put(cab.getCabType(),Integer.valueOf(cab.getCabPriceAccToType()));
		}
		
		return cabTypeList;
	}
	
}
