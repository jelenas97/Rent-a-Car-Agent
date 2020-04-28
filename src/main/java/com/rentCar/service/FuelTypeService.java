package com.rentCar.service;

import com.rentCar.model.CarClass;
import com.rentCar.model.FuelType;

import java.util.List;

public interface FuelTypeService {
    FuelType findOne(Long id);
    List<String> findAllStringList();
}
