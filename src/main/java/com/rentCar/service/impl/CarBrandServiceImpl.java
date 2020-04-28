package com.rentCar.service.impl;

import com.rentCar.model.CarBrand;
import com.rentCar.repository.CarBrandRepository;
import com.rentCar.service.CarBrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class CarBrandServiceImpl implements CarBrandService {

    @Autowired
    private CarBrandRepository carBrandRepository;

    @Override
    public CarBrand findOne(Long id) {
        return carBrandRepository.findById(id).orElse(null);
    }
    @Override
    public List<String> findAllStringList()
    {
        List<String> stringList = new ArrayList<>();
        for(CarBrand  carBrand: carBrandRepository.findAll()){
            stringList.add(carBrand.getName());
        }
        return stringList;
    }
}
