package com.elcocomx.springboot.app.service.banner;

import java.util.List;

import com.elcocomx.springboot.app.model.entity.banner.Banner;
import com.elcocomx.springboot.app.model.entity.product.Product;

public interface IBannerService {

	List<Product> getAllBanners();
	Banner getBannerById(int bannerId);
    boolean addBanner(Banner banner);
    void updateBanner(Banner banner);
    void deleteBanner(int bannerId);
}
