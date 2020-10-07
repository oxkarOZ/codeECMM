package com.elcocomx.springboot.app.model.entity.product;

import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.Cascade;

import com.elcocomx.springboot.app.model.entity.brand.Brand;
import com.elcocomx.springboot.app.model.entity.category.Category;
import com.elcocomx.springboot.app.model.entity.images.Image;

import lombok.Getter;
import lombok.Setter;

@Entity
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Getter @Setter
	private Integer productId;
	
	@Getter @Setter
	private String productTitle;
	
	@Getter @Setter
	private String productDescription;
	
	@Getter @Setter
	private String productSku;
	
	@Getter @Setter
	private Double productPrice;
	
	@Getter @Setter
	private String productSize;
	
	@Getter @Setter
	private String productUpc;
	
	@Getter @Setter
	private String productSatCode;
	
	@Getter @Setter
	private String productLicence;
	
	@Getter @Setter
	private String productStatus;
	
	@Getter @Setter
	private String productName;
	
	@ManyToOne
	@Getter @Setter
	private Brand brand;
	
	@ManyToOne
	@Getter @Setter
	private Category category;
	
	@OneToMany(cascade=CascadeType.PERSIST, fetch = FetchType.LAZY)
	@Getter @Setter
    private List<Image> images;
	
	
}
