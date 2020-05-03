package com.rentCar.service;

import com.rentCar.model.CarBrand;

import java.util.ArrayList;
import java.util.List;

public interface CarBrandService {
    CarBrand findOne(Long id);
    CarBrand findOneByName(String name);
    List<String> findAllStringList();
    void save(String name);
    void delete(String name);
    void setActive(String name);

}
