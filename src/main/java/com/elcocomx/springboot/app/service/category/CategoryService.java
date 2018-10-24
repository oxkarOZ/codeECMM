package com.elcocomx.springboot.app.service.category;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.elcocomx.springboot.app.model.entity.category.Category;
import com.elcocomx.springboot.app.model.entity.category.CategoryRepository;

@Service
public class CategoryService implements ICategoryService{

	@Autowired
	CategoryRepository categoryRepository;
	
	
	@Override
	public List<Category> getAllCategories() {
		List<Category> list = new ArrayList<>();
		categoryRepository.findAll().forEach(e -> list.add(e));
		return list;
	}

	@Override
	public Category getCategoryById(int categoryId) {
		Category obj = categoryRepository.findById(categoryId).get();
		return obj;
	}

	@Override
	public boolean addCategory(Category category) {
		List<Category> list = categoryRepository.findByCategoryNameAndCategoryDescription(category.getCategoryName(), category.getCategoryDescription()); 	
        if (list.size() > 0) {
           return false;
        } else {
	        categoryRepository.save(category);
	        return true;
        }
	}

	@Override
	public void updateCategory(Category category) {
		categoryRepository.save(category);
		
	}

	@Override
	public void deleteCategory(int categoryId) {
		categoryRepository.delete(getCategoryById(categoryId));
		
	}

}
