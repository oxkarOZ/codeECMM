package com.elcocomx.springboot.app.service.image;

import java.util.List;

import com.elcocomx.springboot.app.model.entity.images.Image;
import com.elcocomx.springboot.app.model.entity.product.Product;


public interface IImageService {
	boolean addImage(Image image);
	void deleteImage(int imageId);
	Image getImageById(int imageId);
}
