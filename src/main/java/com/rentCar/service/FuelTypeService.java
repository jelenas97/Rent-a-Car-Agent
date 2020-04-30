package com.rentCar.service;

import com.rentCar.model.CarClass;
import com.rentCar.model.FuelType;

import java.util.List;

public interface FuelTypeService {
    FuelType findOne(Long id);
    List<String> findAllStringList();
    FuelType findOneByName(String name);
    void addFuel(String name);
    void deleteFuel(String name);
    void setActive(String name);
}
