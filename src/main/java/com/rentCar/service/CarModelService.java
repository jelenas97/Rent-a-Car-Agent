package com.rentCar.service;

import com.rentCar.model.CarBrand;
import com.rentCar.model.CarModel;

import java.util.List;

public interface CarModelService {
    CarModel findOne(Long id);

    CarModel findOneByName(String name);


    List<CarModel> findAll(Long id);

    List<CarModel> findAllActive(Long id);

    void save(String name, CarBrand brand);

    void delete(CarModel carModel);

    void setActive(String name);
}
