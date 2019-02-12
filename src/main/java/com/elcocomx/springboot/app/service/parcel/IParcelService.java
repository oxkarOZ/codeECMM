package com.elcocomx.springboot.app.service.parcel;

import java.util.List;

import com.elcocomx.springboot.app.model.entity.parcel.Parcel;


public interface IParcelService {
	List<Parcel> getAllParcel();
	Parcel getParcelById(int parcelId);
    boolean addParcel(Parcel parcel);
    void updateParcel(Parcel parcel);
    void deleteParcel(int parcelId);
}
