package com.elcocomx.springboot.app.controller.product;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.elcocomx.springboot.app.model.entity.product.Product;
import com.elcocomx.springboot.app.service.product.IProductService;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@Controller
@RequestMapping("admin/product")
public class ProductAdminController {
	@Autowired
	IProductService productService;
	
	
	
	@RequestMapping(value="/list", method=RequestMethod.GET)
	public String listar(Model model) {	
		model.addAttribute("titulo", "Listado de productos en Banner");
		model.addAttribute("products", productService.getAllProducts());
		return "listarProducts";
	}
	
	
}
