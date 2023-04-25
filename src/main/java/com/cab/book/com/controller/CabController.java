package com.cab.book.com.controller;

import java.util.List;

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

import com.cab.book.com.payload.CabDto;
import com.cab.book.com.service.CabService;

@RestController
@RequestMapping("/api/v1/cab")
public class CabController {

	@Autowired
	private CabService cabService;
	
	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping("/addCab")
	public ResponseEntity<CabDto> postCab(@RequestBody CabDto cabDto){
		return new ResponseEntity<>(cabService.postCab(cabDto),HttpStatus.CREATED);
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@PutMapping("/updateCab/{cabId}")
	public ResponseEntity<CabDto> updateCab(@RequestBody CabDto cabDto, @PathVariable Integer cabId){
		return new ResponseEntity<>(cabService.updateCab(cabDto, cabId),HttpStatus.OK);
	}
	
	@PreAuthorize("hasRole('USER')|| hasRole('ADMIN')")
	@GetMapping("/listAllCabs")
	public ResponseEntity<List<CabDto>> listAllCabs(){
		return new ResponseEntity<>(cabService.listAllCabs(),HttpStatus.OK);
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping("/deleteCab/{cabId}")
	public ResponseEntity<String> deleteCab(@PathVariable Integer cabId){
		cabService.deleteCab(cabId);
		return new ResponseEntity<>("Cab deleted",HttpStatus.OK);
	}
}
