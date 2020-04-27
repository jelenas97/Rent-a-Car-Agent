package com.rentCar.service.impl;

import com.rentCar.model.CarBrand;
import com.rentCar.model.CarClass;
import com.rentCar.repository.CarBrandRepository;
import com.rentCar.repository.CarClassRepository;
import com.rentCar.service.CarBrandService;
import com.rentCar.service.CarClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CarClassServiceImpl implements CarClassService {

    @Autowired
    private CarClassRepository carClassRepository;

    @Override
    public CarClass findOne(Long id) {
        return carClassRepository.findById(id).orElse(null);
    }
    @Override
    public List<CarClass> findAll() {
        return carClassRepository.findAll();
    }
}
