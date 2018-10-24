package com.elcocomx.springboot.app.model.entity.address;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.Getter;
import lombok.Setter;

@Entity
public class Address {

	@Id
	@GeneratedValue ( strategy = GenerationType.AUTO)
	@Getter @Setter
	private Integer addressId;
	
	@Getter @Setter
	private String addressFullName;
	
	@Getter @Setter
	private String addressPhone;
	
	@Getter @Setter
	private String addressStreet;
	
	@Getter @Setter
	private String addressDetails;
	
	@Getter @Setter
	private String addressStatus;
	
	@ManyToOne
	@Getter @Setter
	private Colony colony;
	
	
}
