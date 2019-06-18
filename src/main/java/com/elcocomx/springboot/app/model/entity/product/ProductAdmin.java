package com.elcocomx.springboot.app.model.entity.product;

import lombok.Getter;
import lombok.Setter;

public class ProductAdmin extends Product{
	
	@Getter @Setter
	private int existInBanner;

	@Getter @Setter
	private int currentProduct;
	
	@Getter @Setter
	private String urlNewImage;
	
	@Getter @Setter
	private String messageError;
	
	@Getter @Setter
	private int errorCode;
}
