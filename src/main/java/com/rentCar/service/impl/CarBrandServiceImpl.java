package com.rentCar.service.impl;

import com.rentCar.model.CarBrand;
import com.rentCar.repository.CarBrandRepository;
import com.rentCar.service.CarBrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public CarBrand findOneByName(String name) {
        return carBrandRepository.findByName(name);
    }


    @Override
    public List<CarBrand> findAll() {
        return carBrandRepository.findAll();
    }

    @Override
    public List<CarBrand> findAllActive() {
        return carBrandRepository.getActiveCarBrands();
    }


    @Override
    public void save(String name) {
        this.carBrandRepository.save(new CarBrand(name));
    }

    @Override
    public void delete(CarBrand carBrand) {
        carBrand.setActive(false);
        this.carBrandRepository.save(carBrand);
    }

    @Override
    public void setActive(String name) {
        CarBrand carBrand = this.carBrandRepository.findByName(name);
        carBrand.setActive(true);
        this.carBrandRepository.save(carBrand);
    }
}
