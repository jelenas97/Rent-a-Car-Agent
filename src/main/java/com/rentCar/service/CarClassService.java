package com.rentCar.service;

import com.rentCar.model.CarClass;

import java.util.List;

public interface CarClassService {
    CarClass findOne(Long id);
    List<String> findAllStringList();
    CarClass findOneByName(String name);
    void save(String name);
    void delete(String name);
    void setActive(String name);

    List<CarClass> findAll();
}
