package com.elcocomx.springboot.app.model.entity.reviewproduct;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import com.elcocomx.springboot.app.model.entity.profile.Profile;
import com.elcocomx.springboot.app.model.entity.sale.SalePlataformProduct;

import lombok.Getter;
import lombok.Setter;

@Entity
public class ReviewProduct {

	@Id
	@GeneratedValue ( strategy = GenerationType.AUTO )
	@Getter @Setter
	private Integer reviewId;
	
	@Getter @Setter
	private Integer reviewStars;
	
	@Getter @Setter
	private String reviewTitle;
	
	@Getter @Setter
	private String reviewDescription;
	
	@OneToOne
	@Getter @Setter
	private SalePlataformProduct salePlataformProduct;
	
	@OneToOne
	@Getter @Setter
	private Profile profile;
	
}
