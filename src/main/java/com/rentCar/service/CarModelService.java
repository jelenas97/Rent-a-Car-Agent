package com.rentCar.service;

import com.rentCar.model.CarClass;
import com.rentCar.model.CarModel;

import java.util.List;

public interface CarModelService {
    CarModel findOne(Long id);
    List<CarModel> findAll();
}
