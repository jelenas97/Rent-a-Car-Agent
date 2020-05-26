package com.rentCar.service;

import com.rentCar.model.FuelType;

import java.util.List;

public interface FuelTypeService {
    FuelType findOne(Long id);

    FuelType findOneByName(String name);

    List<FuelType> findAll();

    List<FuelType> findAllActive();

    void save(String name);

    void delete(FuelType fuelType);

    void setActive(String name);
}
