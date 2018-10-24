package com.elcocomx.springboot.app.service.slide;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.elcocomx.springboot.app.model.entity.slide.Slide;
import com.elcocomx.springboot.app.model.entity.slide.SlideRepository;

@Service
public class SlideService implements ISlideService {
	@Autowired
	private SlideRepository slideRepository;
	
	
	@Override
	public List<Slide> getAllSlides() {
		List<Slide> list = new ArrayList<>();
		slideRepository.findAll().forEach(e -> list.add(e));
		return list;
	}

	@Override
	public Slide getSlideById(int slideId) {
		Slide obj = slideRepository.findById(slideId).get();
		return obj;
	}

	@Override
	public boolean addSlide(Slide slide) {
		List<Slide> list = slideRepository.findByProduct(slide.getProduct()); 	
        if (list.size() > 0) {
           return false;
        } else {
	        slideRepository.save(slide);
	        return true;
        }
	}

	@Override
	public void updateSlide(Slide slide) {
		slideRepository.save(slide);
		
	}

	@Override
	public void deleteSlide(int slideId) {
		slideRepository.delete(getSlideById(slideId));		
	}
	
}
