package com.elcocomx.springboot.app.model.entity.featured;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.elcocomx.springboot.app.model.entity.product.Product;

public interface FeaturedRepository extends CrudRepository<Featured, Integer> {
	List<Featured> findByProduct(Product product);
    
}
