package com.elcocomx.springboot.app.service.slide;

import java.util.List;

import com.elcocomx.springboot.app.model.entity.slide.Slide;

public interface ISlideService {

	List<Slide> getAllSlides();
	Slide getSlideById(int slideId);
    boolean addSlide(Slide slide);
    void updateSlide(Slide slide);
    void deleteSlide(int slideId);
}
