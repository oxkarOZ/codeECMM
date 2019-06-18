package com.elcocomx.springboot.app.controller.category;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.elcocomx.springboot.app.model.entity.category.Category;
import com.elcocomx.springboot.app.service.category.ICategoryService;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@Controller
@RequestMapping("admin")
public class CategoryAdminController {
	@Autowired
	ICategoryService categoryService;
	
	@RequestMapping(value="/category/list", method=RequestMethod.GET)
	public String listProduct(Model model) {	
		model.addAttribute("titulo", "Listado de Categorías");		
		model.addAttribute("categories", categoryService.getAllCategories());		
		return "listarCategories";
	}
	
	@RequestMapping(value="/category/addCategory",method=RequestMethod.GET)
	public String addNewCategory(Model model) {
		Category cate =  new Category();				
		model.addAttribute("category", cate);		
         return "showCategory";
	}
	
	@RequestMapping(value="/category/addCategory/{id}",method=RequestMethod.GET)
	public String addCategory(@PathVariable("id") int id,Model model) {
		Category cate =  new Category();			   	
        cate = categoryService.getCategoryById(id);		
        model.addAttribute("category", cate);		
         return "showCategory";
	}
	
	@RequestMapping(value = "/category/addCategory/", method=RequestMethod.POST)
	public String addCategory(@Valid Category category, BindingResult result, Model model) {		       	
				
	    categoryService.addCategory(category);	        
        
		model.addAttribute("category", category);		
		                
          return "showCategory";
	}
	

}
