package com.elcocomx.springboot.app.model.entity.banner;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;



import com.elcocomx.springboot.app.model.entity.product.Product;

import lombok.Getter;
import lombok.Setter;

@Entity
public class Banner {

	@Id
	@GeneratedValue ( strategy = GenerationType.AUTO )
	@Getter @Setter
	private Integer bannerId;
	
	@OneToOne
	@Getter @Setter
	Product product;	
	
}
