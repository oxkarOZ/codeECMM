package com.elcocomx.springboot.app.service.category;

import java.util.List;

import com.elcocomx.springboot.app.model.entity.category.Category;
import com.elcocomx.springboot.app.model.entity.category.MainCategory;

public interface ICategoryService {

	List<Category> getAllCategories();
	List<MainCategory> getAllMainCategories();
	MainCategory getStartPrice(Category category);
	Category getCategoryById(int categoryId);
    boolean addCategory(Category category);
    void updateCategory(Category category);
    void deleteCategory(int categoryId);
    
	
}
