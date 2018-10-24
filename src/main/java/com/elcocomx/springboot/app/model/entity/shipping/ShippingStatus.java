package com.elcocomx.springboot.app.model.entity.shipping;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;

@Entity
public class ShippingStatus {

	@Id
	@GeneratedValue ( strategy = GenerationType.AUTO )
	@Getter @Setter
	private Integer shippingStatusId;
	
	@Getter @Setter
	private String shippingStatusName;
	
	@Getter @Setter
	private String shippingStatusDescription;
	
}
