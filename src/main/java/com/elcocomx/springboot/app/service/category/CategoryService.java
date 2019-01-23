package com.elcocomx.springboot.app.service.category;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.elcocomx.springboot.app.model.entity.brand.BrandTotal;
import com.elcocomx.springboot.app.model.entity.category.Category;
import com.elcocomx.springboot.app.model.entity.category.CategoryCount;
import com.elcocomx.springboot.app.model.entity.category.CategoryRepository;
import com.elcocomx.springboot.app.model.entity.category.MainCategory;
import com.elcocomx.springboot.app.model.entity.product.ProductRepository;

@Service
public class CategoryService implements ICategoryService{

	@Autowired
	CategoryRepository categoryRepository;
	@Autowired
	ProductRepository productRepository;
	
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



	@Override
	public List<MainCategory> getAllMainCategories() {
		List<MainCategory> lista = new ArrayList<>();
		categoryRepository.findAll().forEach(e -> lista.add(getStartPrice(e)));
		
		return lista;
	}



	@Override
	public MainCategory getStartPrice(Category category) {
		MainCategory mainCategory = new MainCategory();
		mainCategory.setStartPrice(productRepository.getStartPrice(category).get(0));
		mainCategory.setCategoryId(category.getCategoryId());
		mainCategory.setCategoryName(category.getCategoryName());
		mainCategory.setCategoryDescription(category.getCategoryDescription());
		
		
		return mainCategory;
	}


	@Override
	public List<CategoryCount> getAllCategoriesCount() {
		List<CategoryCount> lista = new ArrayList<>();
		categoryRepository.findAll().forEach(e -> lista.add(getCount(e)));
		return lista;
	}
	
	public CategoryCount getCount(Category category) {
		CategoryCount categoryCount = new CategoryCount();
		categoryCount.setCategoryId(category.getCategoryId());
		categoryCount.setCategoryName(category.getCategoryName());
		categoryCount.setCategoryDescription(category.getCategoryDescription());
		categoryCount.setQuantity(productRepository.getCount(category));
		return categoryCount;
	}


}
