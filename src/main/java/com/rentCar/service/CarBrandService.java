package com.rentCar.service;

import com.rentCar.model.CarBrand;

import java.util.List;

public interface CarBrandService {
    CarBrand findOne(Long id);

    CarBrand findOneByName(String name);

    List<CarBrand> findAllActive();

    List<CarBrand> findAll();

    void save(String name);

    void setActive(String name);

    void delete(CarBrand carBrand);

}
