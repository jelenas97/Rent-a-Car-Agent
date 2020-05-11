package com.rentCar.service;

import com.rentCar.model.FuelType;

import java.util.List;

public interface FuelTypeService {
    FuelType findOne(Long id);

    List<String> findAllStringList();

    FuelType findOneByName(String name);

    void save(String name);

    void delete(String name);

    void setActive(String name);

    List<FuelType> findAll();
}
