package com.cab.book.com.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;
import lombok.ToString;

@Entity
@Table(name = "orders")
@Data
public class Order {
	
		@Id
		@GeneratedValue(strategy = GenerationType.AUTO)
		@Column(name = "pid")
		private Integer id;
		
		@Column(name = "paypal_order_id")
		private String paypalOrderId;
		
		@Column
		private String paypalOrderStatus;
		@ToString.Exclude
		@ManyToOne
		@JoinColumn(name="id",referencedColumnName = "id")
		private User userP;
	}

