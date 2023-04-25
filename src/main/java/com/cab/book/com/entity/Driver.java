package com.cab.book.com.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "driver")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Driver{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer driverId;
	
	@Column(name = "drivername")
	private String name;
	
	@Column(name = "password")
	private String password;
	
	@Column(name = "number")
	private Long contactNumber;
	
	private Integer rating;
	
	private boolean occupied;
	
	@ManyToMany(targetEntity = Role.class,cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinTable(name = "driver_roles",
	joinColumns = @JoinColumn(name = "driverid"),
	inverseJoinColumns = @JoinColumn(name = "roleid"))
	private Set<Role> roles = new HashSet<>();
}

