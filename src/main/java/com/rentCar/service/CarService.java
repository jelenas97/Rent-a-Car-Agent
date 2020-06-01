package com.rentCar.service;

import com.rentCar.model.Car;

public interface CarService {
    void add(Car car);

    Car findById(String id);
}
