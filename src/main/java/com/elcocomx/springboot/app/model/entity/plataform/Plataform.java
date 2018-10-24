package com.elcocomx.springboot.app.model.entity.plataform;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;

@Entity
public class Plataform {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Getter @Setter
	private Integer plataformId;
	
	@Getter @Setter
	private String plataformName;
	
	@Getter @Setter
	private String plataformDescription;

	
}
