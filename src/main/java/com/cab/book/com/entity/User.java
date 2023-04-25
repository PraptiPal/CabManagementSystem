package com.cab.book.com.entity;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import com.cab.book.com.util.ContactNumberConstraint;
import com.fasterxml.jackson.annotation.JsonIgnore;


import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@NoArgsConstructor
@Entity
@Inheritance(strategy= InheritanceType.JOINED)
@Table(name = "users")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(unique = true)
	private String username;
	
	@Column(name = "firstname")
	private String firstName;
	
	
	@Column(name = "lastname")
	private String lastName;
	
	
    @JsonIgnore
//    @Getter(onMethod = @__( @JsonIgnore ))
//    @Setter
	private String password;
	
	 
	@Column(name = "email")
	private String emailId;
	
	//@ContactNumberConstraint
	private Long contactNumber;
	
	@Embedded
	@ElementCollection
	@AttributeOverrides({
		  @AttributeOverride( name = "houseNumber", column = @Column(name = "housenumber")),
		  @AttributeOverride( name = "streetName", column = @Column(name = "street")),
		  @AttributeOverride( name = "pincode", column = @Column(name = "pin")),
		  @AttributeOverride( name = "landmark", column = @Column(name = "landmark"))
		})
	private Set <Address> address;
	
	@OneToMany(cascade = CascadeType.ALL,mappedBy="userP")
	private List<Order> orders = new ArrayList<>();
	
	@ManyToMany(targetEntity = Role.class,cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinTable(name = "user_roles",
	joinColumns = @JoinColumn(name = "userid"),
	inverseJoinColumns = @JoinColumn(name = "roleid"))
	private Set<Role> roles = new HashSet<>();
}
