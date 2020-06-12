package com.rentCar.service;

import com.rentCar.model.TransmissionType;

import java.util.List;

public interface TransmissionTypeService {
    TransmissionType findOne(Long id);

    TransmissionType findOneByName(String name);

    List<TransmissionType> findAll();

    List<TransmissionType> findAllActive();

    void save(String name);

    void setActive(String name);

    void delete(TransmissionType transmissionType);
}
