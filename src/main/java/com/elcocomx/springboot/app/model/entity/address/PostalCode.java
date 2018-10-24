package com.elcocomx.springboot.app.model.entity.address;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;

@Entity
public class PostalCode {
	
	@Id
	@GeneratedValue ( strategy = GenerationType.AUTO)
	@Getter @Setter
	private Integer postalCodeId;
	@Getter @Setter
	private String postalCodeNumber;

}
