package com.elcocomx.springboot.app.model.entity.user;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import com.elcocomx.springboot.app.model.entity.profile.Profile;

import lombok.Getter;
import lombok.Setter;

@Entity
public class User {
	
	@Id
	@GeneratedValue ( strategy = GenerationType.AUTO )
	@Getter @Setter
	private Integer userId;
	
	@Getter @Setter
	private String userName;
	
	@Getter @Setter
	private String userPassword;
	
	@OneToOne
	@Getter @Setter
	private Profile profile;

}
