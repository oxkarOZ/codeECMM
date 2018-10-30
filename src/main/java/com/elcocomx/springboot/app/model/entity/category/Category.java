package com.elcocomx.springboot.app.model.entity.category;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
public class Category {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Getter @Setter
	private Integer categoryId;
	
	@Getter @Setter
	private String categoryName;
	
	@Getter @Setter
	private String categoryDescription;
	
}
