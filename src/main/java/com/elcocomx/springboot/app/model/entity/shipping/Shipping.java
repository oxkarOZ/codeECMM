package com.elcocomx.springboot.app.model.entity.shipping;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.elcocomx.springboot.app.model.entity.address.Address;
import com.elcocomx.springboot.app.model.entity.parcel.Parcel;

import lombok.Getter;
import lombok.Setter;

@Entity
public class Shipping {
	
	@Id
	@GeneratedValue ( strategy = GenerationType.AUTO )
	@Getter @Setter
	private Integer shippingId;
	
	@Getter @Setter
	private Date shippingDate;
	
	@Getter @Setter
	private String shippingTrackingNumber;
	
	@Getter @Setter
	private Date shippingDeliveryDate;
	
	@Getter @Setter
	private Date shippingEstimatedDeliveryDate;
	
	@Getter @Setter
	private Double shippingDeliveryCost;
	
	@ManyToOne
	@Getter @Setter
	private ShippingStatus shippingStatus;
	
	@ManyToOne
	@Getter @Setter
	private Parcel parcel;
	
	@ManyToOne
	@Getter @Setter
	private Address address;
	
	

}
