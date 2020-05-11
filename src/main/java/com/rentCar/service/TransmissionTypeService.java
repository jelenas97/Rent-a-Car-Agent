package com.rentCar.service;

import com.rentCar.model.TransmissionType;

import java.util.List;

public interface TransmissionTypeService {
    TransmissionType findOne(Long id);

    List<String> findAllStringList();

    TransmissionType findOneByName(String name);

    void save(String name);

    void delete(String name);

    void setActive(String name);

    List<TransmissionType> findAll();
}
