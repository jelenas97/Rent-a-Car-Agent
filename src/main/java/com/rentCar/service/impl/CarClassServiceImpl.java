package com.rentCar.service.impl;

import com.rentCar.model.CarClass;
import com.rentCar.repository.CarClassRepository;
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

    @Override
    public CarClass findOneByName(String name) {
        return this.carClassRepository.findByName(name);
    }

    @Override
    public void save(String name) {
        this.carClassRepository.save(new CarClass(name));
    }

    @Override
    public void delete(CarClass carClass) {
        carClass.setActive(false);
        this.carClassRepository.save(carClass);

    }

    @Override
    public void setActive(String name) {
        CarClass carClass = this.carClassRepository.findByName(name);
        carClass.setActive(true);
        this.carClassRepository.save(carClass);
    }

    @Override
    public List<CarClass> findAllActive() {
        return carClassRepository.getActiveCarClasses();
    }

}
