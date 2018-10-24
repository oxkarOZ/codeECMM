package com.elcocomx.springboot.app.model.entity.brand;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;

@Entity
public class Brand {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Getter @Setter
	private Integer brandId;

	@Getter @Setter
	private String brandName;

	@Getter @Setter
	private String brandDescription;

	@Getter @Setter
	private String brandImage;

}
