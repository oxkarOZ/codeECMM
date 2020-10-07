package com.elcocomx.springboot.app.model.entity.product;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.elcocomx.springboot.app.model.entity.category.Category;

public interface ProductRepository extends CrudRepository<Product, Integer> {

	List<Product> findByProductName(String productName);	
	List<Product> findByProductNameAndProductSku(String productName, String productSku);
    @Query("SELECT product.productPrice FROM Product product where product.category = ?1 ORDER BY product.productPrice ASC")
	List<Double> getStartPrice(Category category);
    @Query("SELECT count(product.productId) as total FROM Product product where product.category = ?1 ")
	Integer getCount(Category category);
    List<Product> findByCategory(Category category);    
    
    @Query("SELECT p,b.product.productId as Existe FROM Product as p left join Banner as b on p = b.product")
    List<Object[]> getProductBannerInfo();
}
