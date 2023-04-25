package com.cab.book.com.entity;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import lombok.Data;

@Entity
@Data
@Table(name = "roles")
public class Role {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@NotEmpty
	private String roleName;
	 
//	@ManyToMany(targetEntity = User.class,mappedBy = "roles")
//	private Set<User> user = new HashSet<>();
//	
//	@ManyToMany(targetEntity = User.class,mappedBy = "roles")
//	private Set<User> user = new HashSet<>();
	
}
