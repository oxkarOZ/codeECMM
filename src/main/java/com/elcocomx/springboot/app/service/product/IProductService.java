package com.elcocomx.springboot.app.service.product;

import java.util.List;

import com.elcocomx.springboot.app.model.entity.product.Product;
import com.elcocomx.springboot.app.model.entity.product.ProductAdmin;

public interface IProductService {
	List<Product> getAllProducts();
	List<Product> getAllProductsByCategory(int idCategory);
	Product getProductById(int productId);
    boolean addProduct(Product product);
    void updateProduct(Product product);
    void deleteProduct(int productId);
    List<ProductAdmin>getAllProductsInfo();
}
