package com.cab.book.com.payload;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "bookcab")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
public class BookCabDto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer bookingId;
	
	private Integer userId;
	
	private String fromLocation;
	
	private String toLocation;
	
	private Date fromDate;
	
	private Date toDate;
	
	private Integer distance;
	
    private Integer  totalCharge;
    
    private String bookingStatus;
    
    private String cabType;
    
    
    //private List<String> driverDetails;
	//private Driver driver;
    //private Integer driverRating;
}
