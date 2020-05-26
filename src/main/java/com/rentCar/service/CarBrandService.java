package com.rentCar.service;

import com.rentCar.model.CarBrand;

import java.util.List;

public interface CarBrandService {
    CarBrand findOne(Long id);

    CarBrand findOneByName(String name);

    List<CarBrand> findAll();

    List<CarBrand> findAllActive();

    void save(String name);

    void setActive(String name);

    void delete(CarBrand carBrand);

}
