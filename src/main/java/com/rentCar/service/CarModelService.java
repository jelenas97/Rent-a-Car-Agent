package com.rentCar.service;

import com.rentCar.model.CarClass;
import com.rentCar.model.CarModel;

import java.util.List;

public interface CarModelService {
    CarModel findOne(Long id);
    List<String> findAllStringList();
    CarModel findOneByName(String name);
    void addModel(String name);
    void deleteModel(String name);
    void setActive(String name);
}
