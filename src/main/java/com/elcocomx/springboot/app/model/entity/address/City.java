package com.elcocomx.springboot.app.model.entity.address;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.Getter;
import lombok.Setter;

@Entity
public class City {
	
	@Id
	@GeneratedValue ( strategy = GenerationType.AUTO)
	@Getter @Setter
	private Integer cityId;
	
	@Getter @Setter
	private String cityName;
	
	@ManyToOne
	@Getter @Setter
	private State state;
}
