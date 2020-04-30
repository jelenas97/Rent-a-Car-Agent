package com.rentCar.service;

import com.rentCar.model.CarBrand;
import com.rentCar.model.CarClass;

import java.util.List;

public interface CarClassService {
    CarClass findOne(Long id);
    List<String> findAllStringList();
    CarClass findOneByName(String name);
    void addClass(String name);
    void deleteClass(String name);
    void setActive(String name);

}
