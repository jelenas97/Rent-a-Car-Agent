package com.rentCar.service;

import com.rentCar.dto.CarDTO;
import com.rentCar.model.Car;

public interface CarService {
    void add(Car car);

    CarDTO findById(String id);
}
