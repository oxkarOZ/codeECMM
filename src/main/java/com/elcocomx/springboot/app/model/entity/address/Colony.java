package com.elcocomx.springboot.app.model.entity.address;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.Getter;
import lombok.Setter;

@Entity
public class Colony {
	
	@Id
	@GeneratedValue ( strategy = GenerationType.AUTO)
	@Getter @Setter
	private Integer colonyId;
	
	@Getter @Setter
	private String colonyName;
	
	@ManyToOne
	@Getter @Setter
	private PostalCode postalCode;
	
	@ManyToOne
	@Getter @Setter
	private Town town;
	

}
