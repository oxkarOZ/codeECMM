package com.elcocomx.springboot.app.service.product;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.elcocomx.springboot.app.model.entity.category.Category;
import com.elcocomx.springboot.app.model.entity.category.CategoryRepository;
import com.elcocomx.springboot.app.model.entity.images.Image;
import com.elcocomx.springboot.app.model.entity.images.ImageRepository;
import com.elcocomx.springboot.app.model.entity.product.Product;
import com.elcocomx.springboot.app.model.entity.product.ProductRepository;

@Service
public class ProductService implements IProductService{
	@Autowired
	ProductRepository productRepository;
	ImageRepository imageRepository;
	CategoryRepository categoryRepository;
	
	
	@Override
	public List<Product> getAllProducts() {
		List<Product> list = new ArrayList<>();
		productRepository.findAll().forEach(e -> list.add(e));
		return list;
	}
	
	@Override
	public List<Product> getAllProductsByCategory(int idCategory) {
		List<Product> list = new ArrayList<>();
		try {
			Category category = new Category();
			category.setCategoryId(idCategory);
			productRepository.findByCategory(category).forEach(e -> list.add(e));
		}catch(Exception ex) {
			
		}
		
		return list;
	}

	@Override
	public Product getProductById(int productId) {
		Product obj = productRepository.findById(productId).get();
		return obj;
	}

	@Override
	public boolean addProduct(Product product) {
		List<Product> list = productRepository.findByProductNameAndProductSKU(product.getProductName(), product.getProductSKU()); 	
        if (list.size() > 0) {
           return false;
        } else {
        	productRepository.save(product);
	        return true;
        }
	}

	@Override
	public void updateProduct(Product product) {
		productRepository.save(product);
		
	}

	@Override
	public void deleteProduct(int productId) {
		productRepository.delete(getProductById(productId));
		
	}

}
