package com.elcocomx.springboot.app.service.brand;

import java.util.List;

import com.elcocomx.springboot.app.model.entity.brand.Brand;

public interface IBrandService {
	List<Brand> getAllBrands();
    Brand getBrandById(int brandId);
    boolean addBrand(Brand brand);
    void updateBrand(Brand brand);
    void deleteBrand(int brandId);
}
