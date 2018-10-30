package com.elcocomx.springboot.app.service.featured;

import java.util.List;

import com.elcocomx.springboot.app.model.entity.featured.Featured;
import com.elcocomx.springboot.app.model.entity.product.Product;

public interface IFeaturedService {

	List<Product> getAllFeatured();
	Featured getFeaturedById(int featuredId);
    boolean addFeatured(Featured featured);
    void updateFeatured(Featured featured);
    void deleteFeatured(int featuredId);
    
}
