package com.elcocomx.springboot.app.controller.brand;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.elcocomx.springboot.app.model.entity.brand.Brand;
import com.elcocomx.springboot.app.service.brand.IBrandService;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@Controller
@RequestMapping("admin")
public class BrandAdminController {
	@Autowired
	IBrandService brandService;
	
	@RequestMapping(value="/brand/list", method=RequestMethod.GET)
	public String listBrand(Model model) {	
		model.addAttribute("titulo", "Listado de Marcas");		
		model.addAttribute("brands", brandService.getAllBrands());		
		return "listarBrands";
	}
	
	@RequestMapping(value="/brand/addBrand",method=RequestMethod.GET)
	public String addNewBrand(Model model) {
		Brand bran =  new Brand();				
		model.addAttribute("brand", bran);		
         return "showBrand";
	}
	
	@RequestMapping(value="/brand/addBrand/{id}",method=RequestMethod.GET)
	public String addBrand(@PathVariable("id") int id,Model model) {
		Brand bran =  new Brand();			   	
        bran = brandService.getBrandById(id);		
        model.addAttribute("brand", bran);		
         return "showBrand";
	}
	
	@RequestMapping(value = "/brand/addBrand/", method=RequestMethod.POST)
	public String addBrand(@Valid Brand brand, BindingResult result, Model model) {		       	
				
	    brandService.addBrand(brand);	        
        
		model.addAttribute("brand", brand);		
		                
          return "showBrand";
	}
	

}
