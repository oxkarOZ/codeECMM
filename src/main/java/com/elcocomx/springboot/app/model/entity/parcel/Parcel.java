package com.elcocomx.springboot.app.model.entity.parcel;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;

@Entity
public class Parcel {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Getter @Setter
	private Integer parcelId;
	
	@Getter @Setter
	private String parcelName;
	
	@Getter @Setter
	private String parcelDescription;
	
}
