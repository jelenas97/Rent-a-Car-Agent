package com.rentCar.service;

import com.rentCar.model.CarClass;

import java.util.List;

public interface CarClassService {
    CarClass findOne(Long id);

    CarClass findOneByName(String name);

    List<CarClass> findAll();

    List<CarClass> findAllActive();

    void save(String name);

    void setActive(String name);

    void delete(CarClass carClass);
}
