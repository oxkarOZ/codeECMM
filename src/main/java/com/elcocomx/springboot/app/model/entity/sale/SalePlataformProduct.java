package com.elcocomx.springboot.app.model.entity.sale;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import com.elcocomx.springboot.app.model.entity.paymentmethod.PaymentMethod;
import com.elcocomx.springboot.app.model.entity.plataform.Plataform;
import com.elcocomx.springboot.app.model.entity.product.Product;
import com.elcocomx.springboot.app.model.entity.shipping.Shipping;

import lombok.Getter;
import lombok.Setter;

@Entity
public class SalePlataformProduct {

	
	@Id
	@GeneratedValue ( strategy = GenerationType.AUTO )
	@Getter @Setter
	private Integer salePlataformProductId;
	
	@ManyToOne
	@Getter @Setter
	private Sale sale;
	
	@Getter @Setter
	@OneToOne
	private Plataform plataform;
	
	@Getter @Setter
	@OneToOne
	private Product product;
	
	@Getter @Setter
	private Integer priductQuantity;
	
	@OneToOne
	@Getter @Setter
	private PaymentMethod paymentMethod;
	
	@Getter @Setter
	private Double saleUnitPrice;
	
	@Getter @Setter
	private Double saleUnitPriceREV;
	
	@OneToOne
	@Getter @Setter
	private Shipping shipping;
	
}
