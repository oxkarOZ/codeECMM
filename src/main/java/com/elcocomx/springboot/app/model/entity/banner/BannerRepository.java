package com.elcocomx.springboot.app.model.entity.banner;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.elcocomx.springboot.app.model.entity.product.Product;


public interface BannerRepository extends CrudRepository<Banner, Integer>{
	List<Banner> findByProduct(Product product);
}
