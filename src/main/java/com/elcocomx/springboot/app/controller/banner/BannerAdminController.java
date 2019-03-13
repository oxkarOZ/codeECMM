package com.elcocomx.springboot.app.controller.banner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.view.RedirectView;

import com.elcocomx.springboot.app.model.entity.banner.Banner;
import com.elcocomx.springboot.app.service.banner.IBannerService;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@Controller
@RequestMapping("admin/banner")
public class BannerAdminController {
	@Autowired
	IBannerService bannerService;

	@RequestMapping(value="/list", method=RequestMethod.GET)
	public String listar(Model model) {
		model.addAttribute("titulo", "Listado de productos en Banner");
		model.addAttribute("banners", bannerService.getAllBanners());
		return "listarBanner";
	}

	@RequestMapping(value="/index", method=RequestMethod.GET)
	public String index(Model model) {
		model.addAttribute("titulo", "Listado de productos a mostrarse en Banner");
		return "index";
	}
		
	@RequestMapping(value="/delete/{id}")
	public RedirectView  eliminar(@PathVariable("id") int id) {			
		//bannerService.deleteBanner(id); 
		
		RedirectView rv = new RedirectView();
		rv.setContextRelative(true);  
        rv.setUrl("/admin/banner/list");
        return rv;		
	}
	
	@RequestMapping(value="/add/{id}")
	public RedirectView agregar(@PathVariable("id") int id) {
        Banner banner = new Banner();
        banner.getProduct().setProductId(id);  	
		boolean flag = bannerService.addBanner(banner);
                if (flag == false) {
                }
                RedirectView rv = new RedirectView();
        		rv.setContextRelative(true);  
                rv.setUrl("/admin/banner/list");
                return rv;
	}
	
}
