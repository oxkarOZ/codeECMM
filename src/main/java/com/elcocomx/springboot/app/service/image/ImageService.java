package com.elcocomx.springboot.app.service.image;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.elcocomx.springboot.app.model.entity.images.Image;
import com.elcocomx.springboot.app.model.entity.images.ImageRepository;
import com.elcocomx.springboot.app.model.entity.product.Product;
@Service
public class ImageService implements IImageService{
	@Autowired
	ImageRepository imageRepository;
	
	@Override
	public boolean addImage(Image image) {
		imageRepository.save(image);
		return false;
	}

	@Override
	public void deleteImage(int imageId) {
		imageRepository.delete(getImageById(imageId));		
	}
	
	@Override
	public Image getImageById(int imageId) {
		Image obj = imageRepository.findById(imageId).get();
		return obj;
	}
}
