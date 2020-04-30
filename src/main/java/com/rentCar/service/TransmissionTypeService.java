package com.rentCar.service;

import com.rentCar.model.CarClass;
import com.rentCar.model.TransmissionType;

import java.util.List;

public interface TransmissionTypeService {
    TransmissionType findOne(Long id);
    List<String> findAllStringList();
    TransmissionType findOneByName(String name);
    void addTransmission(String name);
    void deleteTransmission(String name);
    void setActive(String name);
}
