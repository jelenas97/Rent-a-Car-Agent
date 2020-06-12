package com.rentCar.service.impl;

import com.rentCar.model.CarBrand;
import com.rentCar.model.CarModel;
import com.rentCar.repository.CarModelRepository;
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
    public CarModel findOneByName(String name) {
        return this.carModelRepository.findByName(name);
    }

    @Override
    public List<CarModel> findAll(Long id) {
        return carModelRepository.findAll();
    }

    @Override
    public List<CarModel> findAllActive(Long id) {
        return carModelRepository.getActiveCarModels(id);
    }

    @Override
    public void save(String name, CarBrand brand) {
        this.carModelRepository.save(new CarModel(name, brand));
    }

    @Override
    public void delete(CarModel carModel) {
        carModel.setActive(false);
        this.carModelRepository.save(carModel);

    }

    @Override
    public void setActive(String name) {
        CarModel carModel = this.carModelRepository.findByName(name);
        carModel.setActive(true);
        this.carModelRepository.save(carModel);
    }
}
