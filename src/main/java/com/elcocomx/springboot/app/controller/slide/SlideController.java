package com.elcocomx.springboot.app.controller.slide;

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
import com.elcocomx.springboot.app.model.entity.slide.Slide;
import com.elcocomx.springboot.app.service.slide.ISlideService;

@RestController
@RequestMapping("slide")
public class SlideController {
	@Autowired
	ISlideService slideService;

	
	@GetMapping("get/{id}")
	public ResponseEntity<Slide> getSlideById(@PathVariable("id") Integer id) {
		Slide slide = slideService.getSlideById(id);
		return new ResponseEntity<Slide>(slide, HttpStatus.OK);
	}
	
	@GetMapping("all")
	public ResponseEntity<List<Slide>> getAllSlides() {
		List<Slide> list = slideService.getAllSlides();
		return new ResponseEntity<List<Slide>>(list, HttpStatus.OK);
	}
	
	@PostMapping("add")
	public ResponseEntity<Void> addSlide(@RequestBody Slide slide, UriComponentsBuilder builder) {
                boolean flag = slideService.addSlide(slide);
                if (flag == false) {
        	   return new ResponseEntity<Void>(HttpStatus.CONFLICT);
                }
                HttpHeaders headers = new HttpHeaders();
                headers.setLocation(builder.path("/brand/{id}").buildAndExpand(slide.getSlideId()).toUri());
                return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	}
	
	@PutMapping("update")
	public ResponseEntity<Slide> updateBrand(@RequestBody Slide slide) {
		slideService.updateSlide(slide);
		return new ResponseEntity<Slide>(slide, HttpStatus.OK);
	}
	
	@DeleteMapping("delete/{id}")
	public ResponseEntity<Void> deleteSlide(@PathVariable("id") Integer id) {
		slideService.deleteSlide(id);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}
	
	
	
}
