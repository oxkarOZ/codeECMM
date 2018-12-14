package com.elcocomx.springboot.app.model.entity.category;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.elcocomx.springboot.app.model.entity.category.Category;

public interface CategoryRepository extends CrudRepository<Category, Integer> {

	List<Category> findByCategoryName(String categoryName);
    List<Category> findByCategoryNameAndCategoryDescription(String categoryName, String categoryDescription);
    
}
