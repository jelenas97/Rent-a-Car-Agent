package com.rentCar.service;

import com.rentCar.model.CarClass;
import com.rentCar.model.TransmissionType;

import java.util.List;

public interface TransmissionTypeService {
    TransmissionType findOne(Long id);
    List<String> findAllStringList();
}
