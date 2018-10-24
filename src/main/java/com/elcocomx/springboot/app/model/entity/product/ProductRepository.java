package com.elcocomx.springboot.app.model.entity.product;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends CrudRepository<Product, Integer> {

	List<Product> findByProductName(String productName);	
	List<Product> findByProductNameAndProductSKU(String productName, String productSKU);

    
}
