package com.elcocomx.springboot.app.model.entity.featured;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.elcocomx.springboot.app.model.entity.product.Product;

import lombok.Getter;
import lombok.Setter;

@Entity
public class Featured {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Getter @Setter
	private Integer featuredId;
	
	@ManyToOne
	@Getter @Setter
	private Product product;

}
