package com.elcocomx.springboot.app.controller.banner;

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

import com.elcocomx.springboot.app.model.entity.banner.Banner;
import com.elcocomx.springboot.app.model.entity.brand.Brand;
import com.elcocomx.springboot.app.model.entity.product.Product;
import com.elcocomx.springboot.app.service.banner.IBannerService;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("banner")
public class BannerController {
	@Autowired
	IBannerService bannerService;

	
	@GetMapping("get/{id}")
	public ResponseEntity<Banner> getBannerById(@PathVariable("id") Integer id) {
		Banner banner = bannerService.getBannerById(id);
		return new ResponseEntity<Banner>(banner, HttpStatus.OK);
	}
	
	@GetMapping("all")
	public ResponseEntity<List<Product>> getAllBanners() {
		List<Product> list = bannerService.getAllBanners();
		return new ResponseEntity<List<Product>>(list, HttpStatus.OK);
	}
	
	@PostMapping("add")
	public ResponseEntity<Void> addBanner(@RequestBody Banner banner, UriComponentsBuilder builder) {
                boolean flag = bannerService.addBanner(banner);
                if (flag == false) {
        	   return new ResponseEntity<Void>(HttpStatus.CONFLICT);
                }
                HttpHeaders headers = new HttpHeaders();
                headers.setLocation(builder.path("/banner/{id}").buildAndExpand(banner.getBannerId()).toUri());
                return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	}
	
	@PutMapping("update")
	public ResponseEntity<Banner> updateBanner(@RequestBody Banner banner) {
		bannerService.updateBanner(banner);
		return new ResponseEntity<Banner>(banner, HttpStatus.OK);
	}
	
	@DeleteMapping("delete/{id}")
	public ResponseEntity<Void> deleteBanner(@PathVariable("id") Integer id) {
		bannerService.deleteBanner(id);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}	
	
}
