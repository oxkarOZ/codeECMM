package com.elcocomx.springboot.app.service.product;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.elcocomx.springboot.app.model.entity.category.Category;
import com.elcocomx.springboot.app.model.entity.category.CategoryRepository;
import com.elcocomx.springboot.app.model.entity.images.ImageRepository;
import com.elcocomx.springboot.app.model.entity.product.Product;
import com.elcocomx.springboot.app.model.entity.product.ProductAdmin;
import com.elcocomx.springboot.app.model.entity.product.ProductRepository;
import com.elcocomx.springboot.app.model.entity.banner.BannerRepository;
import com.elcocomx.springboot.app.model.entity.brand.Brand;
import com.elcocomx.springboot.app.model.entity.brand.BrandTotal;
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
	public List<ProductAdmin> getAllProductsInfo() {
		
		List<ProductAdmin> list = new ArrayList<>();
		List<Object[]> results = productRepository.getProductBannerInfo();
		for (Object[] result : results) {
			Product product = new Product();
			ProductAdmin prodAdmin = new ProductAdmin();
			
			product = (Product) result[0];
			
			int existe = (result[1] == null)?0:(int)result[1];
			
			prodAdmin.setProductId(product.getProductId());
			prodAdmin.setProductName(product.getProductName());
			prodAdmin.setProductDescription(product.getProductDescription());
			prodAdmin.setProductPrice(product.getProductPrice());
			prodAdmin.setBrand(product.getBrand());
			prodAdmin.setCategory(product.getCategory());
			prodAdmin.setImages(product.getImages());
			prodAdmin.setExistInBanner(existe);			
			
			list.add(prodAdmin);			
		}
		
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
		Product prod = null;
		
		if(product.getProductId() != null) {
			prod =  productRepository.findById(product.getProductId()).get();
		}	
		
		
		if(prod != null) {
			productRepository.save(product);
			return true;
		}else {
			List<Product> list = productRepository.findByProductNameAndProductSku(product.getProductName(), product.getProductSku()); 	
	        			
			if (list.size() > 0) {
	              return false; 
	        } else {
	        	productRepository.save(product);
		        return true;
	        }
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
