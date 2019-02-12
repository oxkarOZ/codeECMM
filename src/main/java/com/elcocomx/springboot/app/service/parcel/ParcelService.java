package com.elcocomx.springboot.app.service.parcel;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.elcocomx.springboot.app.model.entity.parcel.Parcel;
import com.elcocomx.springboot.app.model.entity.parcel.ParcelRepository;
import com.elcocomx.springboot.app.model.entity.product.Product;

@Service
public class ParcelService implements IParcelService{
	@Autowired
	ParcelRepository parcelRepository;
	
	@Override
	public List<Parcel> getAllParcel() {
		List<Parcel> list = new ArrayList<>();
		parcelRepository.findAll().forEach(e -> list.add(e));
		return list;
	}

	@Override
	public Parcel getParcelById(int parcelId) {
		Parcel obj = parcelRepository.findById(parcelId).get();
		return obj;
	}

	@Override
	public boolean addParcel(Parcel parcel) {
		
		List<Parcel> list = parcelRepository.findByParcelName(parcel.getParcelName()); 	
        if (list.size() > 0) {
           return false;
        } else {
        	parcelRepository.save(parcel);
	        return true;
        }
	}

	@Override
	public void updateParcel(Parcel parcel) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteParcel(int parcelId) {
		// TODO Auto-generated method stub
		
	}
	
}
