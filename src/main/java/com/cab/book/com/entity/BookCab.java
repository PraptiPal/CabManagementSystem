package com.cab.book.com.entity;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "bookcab")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookCab {

	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer bookingId;
	
	private Integer userId;
	
	//@NotBlank(message = "Please enter the source destination")
	private String fromLocation;
	
	//@NotBlank(message = "Please enter the final destination")
	private String toLocation;
	
	
	private Date fromDate;
	
	private Date toDate;
	
	private Integer distance;
	
    private Integer  totalCharge;
    
    private String cabType;
    
    private String bookingStatus;
    
    //private Integer driverRating;
   // @Transient
//   // @ElementCollection(targetClass=String.class)
   
//    
    @ManyToOne
	   @JoinColumn(name="driver_id",referencedColumnName = "driverId")
	private Driver driver;
    
    
    //@Access(AccessType.PROPERTY)
    @Transient  // does not persist in the table
    private String driverField;
//    @ElementCollection
//    private  List<String> driverDetails = new ArrayList<>();
//    @Transient
//    public String driverDetails() {
//       return driverDetails;
//    }
//
//    public void setdriverDetails(String driverDetails) {
//       this.driverDetails = driverDetails;
//    }
    
}
