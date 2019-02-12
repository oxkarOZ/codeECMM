package com.elcocomx.springboot.app.model.entity.parcel;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface ParcelRepository extends CrudRepository<Parcel, Integer> {

	List<Parcel> findByParcelName(String parcelName);
}
