package com.rentCar.service.impl;

import com.rentCar.model.CarModel;
import com.rentCar.model.FuelType;
import com.rentCar.repository.FuelTypeRepository;
import com.rentCar.service.CarModelService;
import com.rentCar.service.FuelTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
    public List<String> findAllStringList()
    {
        List<String> stringList = new ArrayList<>();
        for(FuelType  fuelType: fuelTypeRepository.findAll()){
            stringList.add(fuelType.getName());
        }
        return stringList;
    }
}
