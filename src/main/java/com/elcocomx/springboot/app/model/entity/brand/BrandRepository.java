package com.elcocomx.springboot.app.model.entity.brand;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface BrandRepository extends CrudRepository<Brand, Integer> {
	
	
	List<Brand> findByBrandName(String brandName);
    List<Brand> findByBrandNameAndBrandDescription(String brandName, String brandDescription);
    @Query("SELECT brand, count(product.productId) AS total from Brand brand inner join Product product on product.brand = brand group by brand.brandId")
    List<Object[]> getBrandsTotal();
}
