package com.rentCar.service;

import com.rentCar.model.CarBrand;
import com.rentCar.model.CarClass;

import java.util.List;

public interface CarClassService {
    CarClass findOne(Long id);
    List<String> findAllStringList();
}
