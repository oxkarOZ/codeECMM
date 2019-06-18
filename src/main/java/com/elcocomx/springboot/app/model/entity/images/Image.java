package com.elcocomx.springboot.app.model.entity.images;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Size;

import com.elcocomx.springboot.app.model.entity.product.Product;

import lombok.Getter;
import lombok.Setter;

@Entity
public class Image {

	@Id
	@Getter @Setter
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer imageId;
	
	@Size(max=550)
	@Getter @Setter
	private String imageUrl;
	
	
	
}
