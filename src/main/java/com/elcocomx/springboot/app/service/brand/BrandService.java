package com.elcocomx.springboot.app.service.brand;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.elcocomx.springboot.app.model.entity.brand.Brand;
import com.elcocomx.springboot.app.model.entity.brand.BrandRepository;
import com.elcocomx.springboot.app.model.entity.brand.BrandTotal;

@Service
public class BrandService implements IBrandService{
	@Autowired
	private BrandRepository brandRepository;
	
	
	
	@Override
	public List<Brand> getAllBrands() {
		List<Brand> list = new ArrayList<>();
		brandRepository.findAll().forEach(e -> list.add(e));
		return list;
	}

	@Override
	public Brand getBrandById(int brandId) {
		Brand obj = brandRepository.findById(brandId).get();
		return obj;
	}

	@Override
	public boolean addBrand(Brand brand) {
		List<Brand> list = brandRepository.findByBrandNameAndBrandDescription(brand.getBrandName(), brand.getBrandDescription()); 	
        if (list.size() > 0) {
           return false;
        } else {
	        brandRepository.save(brand);
	        return true;
        }
	}

	@Override
	public void updateBrand(Brand brand) {
		brandRepository.save(brand);
		
	}

	@Override
	public void deleteBrand(int brandId) {
		brandRepository.delete(getBrandById(brandId));
	}

	@Override
	public List<BrandTotal> getAllBrandsTotal() {
		List<BrandTotal> list = new ArrayList<>();
		List<Object[]> results = brandRepository.getBrandsTotal();
		for (Object[] result : results) {
			Brand brand = new Brand();
			BrandTotal brandTotal = new BrandTotal();
			
			brand = (Brand) result[0];
			Long total = (Long)result[1];
			
			brandTotal.setBrandDescription(brand.getBrandDescription());
			brandTotal.setBrandId(brand.getBrandId());
			brandTotal.setBrandImage(brand.getBrandImage());
			brandTotal.setBrandName(brand.getBrandName());
			brandTotal.setTotal(total);
			list.add(brandTotal);
			
		}
		
		return list;
	}
	
}
