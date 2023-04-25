package com.cab.book.com.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "cab")
@Data
public class Cab {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	@Column(name = "cabnumber")
	private String cabNumber;
	
	@Column(name="cabtype")
	private String cabType;
	
	@Column(name="cabprice")
	private String cabPriceAccToType;

}
