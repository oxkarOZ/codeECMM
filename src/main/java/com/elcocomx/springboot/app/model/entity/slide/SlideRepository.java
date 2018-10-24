package com.elcocomx.springboot.app.model.entity.slide;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.elcocomx.springboot.app.model.entity.product.Product;


public interface SlideRepository extends CrudRepository<Slide, Integer>{
	List<Slide> findByProduct(Product product);
}
