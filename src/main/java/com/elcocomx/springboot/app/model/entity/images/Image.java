package com.elcocomx.springboot.app.model.entity.images;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.elcocomx.springboot.app.model.entity.product.Product;

import lombok.Getter;
import lombok.Setter;

@Entity
public class Image {

	@Id
	@Getter @Setter
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer imageId;
	
	@Getter @Setter
	private String imageUrl;
	
	
	
}
