package com.rentCar.service.impl;

import com.rentCar.model.CarClass;
import com.rentCar.model.CarModel;
import com.rentCar.repository.CarModelRepository;
import com.rentCar.service.CarClassService;
import com.rentCar.service.CarModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CarModelServiceImpl implements CarModelService {

    @Autowired
    private CarModelRepository carModelRepository;

    @Override
    public CarModel findOne(Long id) {
        return carModelRepository.findById(id).orElse(null);
    }
    @Override
    public List<CarModel> findAll() {
        return carModelRepository.findAll();
    }
}
