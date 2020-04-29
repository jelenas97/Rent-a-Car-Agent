package com.rentCar.service.impl;

import com.rentCar.model.CarBrand;
import com.rentCar.model.CarClass;
import com.rentCar.repository.CarBrandRepository;
import com.rentCar.repository.CarClassRepository;
import com.rentCar.service.CarBrandService;
import com.rentCar.service.CarClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
@Service
public class CarClassServiceImpl implements CarClassService {

    @Autowired
    private CarClassRepository carClassRepository;

    @Override
    public CarClass findOne(Long id) {
        return carClassRepository.findById(id).orElse(null);
    }
    @Override
    public List<String> findAllStringList()
    {
        return carClassRepository.findAll().stream()
                .map( Object::toString )
                .collect( Collectors.toList() );
    }

    @Override
    public CarClass findOneByName(String name) {
        return this.carClassRepository.findByName(name);
    }

    @Override
    public void addClass(String name) {
        this.carClassRepository.save(new CarClass(name));
    }

    @Override
    public void deleteClass(String name) {
        CarClass carClass = this.carClassRepository.findByName(name);
        carClass.setActive(false);
        this.carClassRepository.save(carClass);

    }

    @Override
    public void setActive(String name) {
        CarClass carClass = this.carClassRepository.findByName(name);
        carClass.setActive(true);
        this.carClassRepository.save(carClass);
    }
}
