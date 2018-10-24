package com.elcocomx.springboot.app.controller.brand;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.elcocomx.springboot.app.model.entity.brand.Brand;
import com.elcocomx.springboot.app.service.brand.IBrandService;

@RestController
@RequestMapping("brand")
public class BrandController {
	@Autowired
	IBrandService brandService;

	@GetMapping("get/{id}")
	public ResponseEntity<Brand> getBrandById(@PathVariable("id") Integer id) {
		Brand brand = brandService.getBrandById(id);
		return new ResponseEntity<Brand>(brand, HttpStatus.OK);
	}
	
	@GetMapping("all")
	public ResponseEntity<List<Brand>> getAllBrands() {
		List<Brand> list = brandService.getAllBrands();
		return new ResponseEntity<List<Brand>>(list, HttpStatus.OK);
	}
	
	@PostMapping("add")
	public ResponseEntity<Void> addBrand(@RequestBody Brand brand, UriComponentsBuilder builder) {
                boolean flag = brandService.addBrand(brand);
                if (flag == false) {
        	   return new ResponseEntity<Void>(HttpStatus.CONFLICT);
                }
                HttpHeaders headers = new HttpHeaders();
                headers.setLocation(builder.path("/brand/{id}").buildAndExpand(brand.getBrandId()).toUri());
                return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	}
	
	@PutMapping("update")
	public ResponseEntity<Brand> updateBrand(@RequestBody Brand brand) {
		brandService.updateBrand(brand);
		return new ResponseEntity<Brand>(brand, HttpStatus.OK);
	}
	
	@DeleteMapping("delete/{id}")
	public ResponseEntity<Void> deleteBrand(@PathVariable("id") Integer id) {
		brandService.deleteBrand(id);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}
	
	
	
}