package com.elcocomx.springboot.app.model.entity.profile;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;

@Entity
public class Profile {
	
	@Id
	@GeneratedValue (strategy = GenerationType.AUTO )
	@Getter @Setter
	private Integer profileId;
	
	@Getter @Setter
	private String profileName;
	
	@Getter @Setter
	private String profileSurname;
	
	@Getter @Setter
	private String profileSecondSurname;
	
	@Getter @Setter
	private Date profileBirthday;
	
	@Getter @Setter
	private String profileMail;

	
}
