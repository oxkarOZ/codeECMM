package com.elcocomx.springboot.app.model.entity.product;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.elcocomx.springboot.app.model.entity.category.Category;

public interface ProductRepository extends CrudRepository<Product, Integer> {

	List<Product> findByProductName(String productName);	
	List<Product> findByProductNameAndProductSKU(String productName, String productSKU);
    @Query("SELECT product.productPrice FROM Product product where product.category = ?1 ORDER BY product.productPrice ASC")
	List<Double> getStartPrice(Category category);
    
}
