package com.elcocomx.springboot.app.controller.featured;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.elcocomx.springboot.app.model.entity.featured.Featured;
import com.elcocomx.springboot.app.model.entity.product.Product;
import com.elcocomx.springboot.app.service.featured.IFeaturedService;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("featured")
public class FeaturedController {
	
	@Autowired
	IFeaturedService featuredService;

	@GetMapping("get/{id}")
	public ResponseEntity<Featured> getFeaturedById(@PathVariable("id") Integer id) {
		Featured featured = featuredService.getFeaturedById(id);
		return new ResponseEntity<Featured>(featured, HttpStatus.OK);
	}
	
	@GetMapping("all")
	public ResponseEntity<List<Product>> getAllFeatureds() {
		List<Product> list = featuredService.getAllFeatured();
		return new ResponseEntity<List<Product>>(list, HttpStatus.OK);
	}
	
	@PostMapping("add")
	public ResponseEntity<Void> addFeatured(@RequestBody Featured featured, UriComponentsBuilder builder) {
                boolean flag = featuredService.addFeatured(featured);
                if (flag == false) {
        	   return new ResponseEntity<Void>(HttpStatus.CONFLICT);
                }
                HttpHeaders headers = new HttpHeaders();
                headers.setLocation(builder.path("/featured/{id}").buildAndExpand(featured.getFeaturedId()).toUri());
                return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	}
	
	@PutMapping("update")
	public ResponseEntity<Featured> updateFeatured(@RequestBody Featured featured) {
		featuredService.updateFeatured(featured);
		return new ResponseEntity<Featured>(featured, HttpStatus.OK);
	}
	
	@DeleteMapping("delete/{id}")
	public ResponseEntity<Void> deleteFeatured(@PathVariable("id") Integer id) {
		featuredService.deleteFeatured(id);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}
	
}
