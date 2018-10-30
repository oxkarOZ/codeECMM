package com.elcocomx.springboot.app.service.banner;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.elcocomx.springboot.app.model.entity.banner.BannerRepository;
import com.elcocomx.springboot.app.model.entity.product.Product;
import com.elcocomx.springboot.app.model.entity.banner.Banner;

@Service
public class BannerService implements IBannerService {
	@Autowired
	private BannerRepository bannerRepository;
	
	
	@Override
	public List<Product> getAllBanners() {
		List<Product> list = new ArrayList<>();
		bannerRepository.findAll().forEach(e -> list.add(e.getProduct()));
		return list;
	}

	@Override
	public Banner getBannerById(int bannerId) {
		Banner obj = bannerRepository.findById(bannerId).get();
		return obj;
	}

	@Override
	public boolean addBanner(Banner banner) {
		List<Banner> list = bannerRepository.findByProduct(banner.getProduct()); 	
        if (list.size() > 0) {
           return false;
        } else {
	        bannerRepository.save(banner);
	        return true;
        }
	}

	@Override
	public void updateBanner(Banner banner) {
		bannerRepository.save(banner);
		
	}

	@Override
	public void deleteBanner(int bannerId) {
		bannerRepository.delete(getBannerById(bannerId));		
	}
	
}
