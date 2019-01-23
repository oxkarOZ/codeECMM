package com.elcocomx.springboot.app.controller.category;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.elcocomx.springboot.app.model.entity.brand.Brand;
import com.elcocomx.springboot.app.model.entity.category.Category;
import com.elcocomx.springboot.app.model.entity.category.CategoryCount;
import com.elcocomx.springboot.app.model.entity.category.MainCategory;
import com.elcocomx.springboot.app.service.category.ICategoryService;
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("category")
public class CategoryController {
	@Autowired
	ICategoryService categoryService;
	
	@GetMapping("get/{id}")
	public ResponseEntity<Category> getCategoryById(@PathVariable("id") Integer id) {
		Category category = categoryService.getCategoryById(id);
		return new ResponseEntity<Category>(category, HttpStatus.OK);
	}
	
	@GetMapping("all")
	public ResponseEntity<List<Category>> getAllCategories() {
		List<Category> list = categoryService.getAllCategories();
		return new ResponseEntity<List<Category>>(list, HttpStatus.OK);
	}
	
	@GetMapping("allCount")
	public ResponseEntity<List<CategoryCount>> getAllCategoriesCount() {
		List<CategoryCount> list = categoryService.getAllCategoriesCount();
		return new ResponseEntity<List<CategoryCount>>(list, HttpStatus.OK);
	}
	
	@GetMapping("allMain")
	public ResponseEntity<List<MainCategory>> getAllMainCategories() {
		List<MainCategory> list = categoryService.getAllMainCategories();
		return new ResponseEntity<List<MainCategory>>(list, HttpStatus.OK);
	}
	
	@PostMapping("add")
	public ResponseEntity<Void> addCategory(@RequestBody Category category, UriComponentsBuilder builder) {
                boolean flag = categoryService.addCategory(category);
                if (flag == false) {
                	return new ResponseEntity<Void>(HttpStatus.CONFLICT);
                }
                HttpHeaders headers = new HttpHeaders();
                headers.setLocation(builder.path("/category/{id}").buildAndExpand(category.getCategoryId()).toUri());
                return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	}
	
	@PutMapping("update")
	public ResponseEntity<Category> updateCategory(@RequestBody Category category) {
		categoryService.updateCategory(category);
		return new ResponseEntity<Category>(category, HttpStatus.OK);
	}
	
	@DeleteMapping("delete/{id}")
	public ResponseEntity<Void> deleteCategory(@PathVariable("id") Integer id) {
		categoryService.deleteCategory(id);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}
	

}
