package com.elcocomx.springboot.app.controller.parcel;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.elcocomx.springboot.app.model.entity.parcel.Parcel;
import com.elcocomx.springboot.app.model.entity.product.Product;
import com.elcocomx.springboot.app.service.parcel.IParcelService;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("parcel")
public class ParcelController {
	@Autowired
	IParcelService parcelService;

	@GetMapping("all")
	public ResponseEntity<List<Parcel>> getAllProducts() {
		List<Parcel> list = parcelService.getAllParcel();
		return new ResponseEntity<List<Parcel>>(list, HttpStatus.OK);
	}
	
	@GetMapping("get/{parcelId}")
	public ResponseEntity<Parcel> getParcelById(@PathVariable("parcelId") Integer parcelId) {
		Parcel parcel = parcelService.getParcelById(parcelId);
		return new ResponseEntity<Parcel>(parcel, HttpStatus.OK);
	}
	
	@PostMapping("add")
	public ResponseEntity<Void> addParcel(@RequestBody Parcel parcel, UriComponentsBuilder builder) {
                boolean flag = parcelService.addParcel(parcel);
                if (flag == false) {
        	   return new ResponseEntity<Void>(HttpStatus.CONFLICT);
                }
                HttpHeaders headers = new HttpHeaders();
                headers.setLocation(builder.path("/parcel/{id}").buildAndExpand(parcel.getParcelId()).toUri());
                return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	}
}
