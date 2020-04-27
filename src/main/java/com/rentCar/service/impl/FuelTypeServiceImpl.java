package com.rentCar.service.impl;

import com.rentCar.model.CarModel;
import com.rentCar.model.FuelType;
import com.rentCar.repository.FuelTypeRepository;
import com.rentCar.service.CarModelService;
import com.rentCar.service.FuelTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class FuelTypeServiceImpl implements FuelTypeService {

    @Autowired
    private FuelTypeRepository fuelTypeRepository;

    @Override
    public FuelType findOne(Long id) {
        return fuelTypeRepository.findById(id).orElse(null);
    }
    @Override
    public List<FuelType> findAll() {
        return fuelTypeRepository.findAll();
    }
}
