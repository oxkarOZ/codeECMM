package com.elcocomx.springboot.app.model.entity.brand;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface BrandRepository extends CrudRepository<Brand, Integer> {

	List<Brand> findByBrandName(String brandName);
    List<Brand> findByBrandNameAndBrandDescription(String brandName, String brandDescription);
	
}
