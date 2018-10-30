package com.elcocomx.springboot.app.service.featured;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.elcocomx.springboot.app.model.entity.featured.Featured;
import com.elcocomx.springboot.app.model.entity.featured.FeaturedRepository;
import com.elcocomx.springboot.app.model.entity.product.Product;

@Service
public class FeaturedService implements IFeaturedService{

	@Autowired
	FeaturedRepository featuredRepository;
	
	@Override
	public List<Product> getAllFeatured() {
		List<Product> list = new ArrayList<>();
		featuredRepository.findAll().forEach(e -> list.add(e.getProduct()));
		return list;
	}

	@Override
	public Featured getFeaturedById(int featuredId) {
		Featured obj = featuredRepository.findById(featuredId).get();
		return obj;
	}

	@Override
	public boolean addFeatured(Featured featured) {
		List<Featured> list = featuredRepository.findByProduct(featured.getProduct());
        if (list.size() > 0) {
           return false;
        } else {
        	featuredRepository.save(featured);
	        return true;
        }
	}

	@Override
	public void updateFeatured(Featured featured) {
		featuredRepository.save(featured);
	}

	@Override
	public void deleteFeatured(int featuredId) {
		featuredRepository.delete(getFeaturedById(featuredId));
	}

}
