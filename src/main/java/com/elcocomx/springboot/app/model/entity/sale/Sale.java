package com.elcocomx.springboot.app.model.entity.sale;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import com.elcocomx.springboot.app.model.entity.profile.Profile;
import com.elcocomx.springboot.app.model.entity.shipping.ShippingStatus;

import lombok.Getter;
import lombok.Setter;

@Entity
public class Sale {
	
	@Id
	@GeneratedValue ( strategy = GenerationType.AUTO )
	@Getter @Setter
	private Integer saleId;
	
	@Getter @Setter
	private Date saleDate;
	
	@Getter @Setter
	private Double saleTotal;
	
	@OneToOne
	@Getter @Setter
	private Profile profile;
	
	@OneToOne
	@Getter @Setter
	private ShippingStatus shippingStatus;
	
	@OneToOne
	@Getter @Setter
	private SaleStatus saleStatus;

}
