package com.elcocomx.springboot.app.model.entity.paymentmethod;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;

@Entity
public class PaymentMethod {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Getter @Setter
	private Integer paymentMethodId;
	
	@Getter @Setter
	private String paymentMethodName;
	
	@Getter @Setter
	private String paymentMethodDescription;
	
	@Getter @Setter
	private Float paymentMethodCommission;
	
	//PaymentMethod_FixedCommission
	@Getter @Setter
	private Float paymentMethodFC;
	
	//PaymentMethod_MonthlyCommision
	@Getter @Setter
	private Float paymentMethodMC;

	
}
