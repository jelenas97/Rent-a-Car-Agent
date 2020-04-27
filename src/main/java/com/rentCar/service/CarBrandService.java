package com.rentCar.service;

import com.rentCar.model.CarBrand;

import java.util.ArrayList;
import java.util.List;

public interface CarBrandService {
    CarBrand findOne(Long id);
    List<CarBrand> findAll();
}
