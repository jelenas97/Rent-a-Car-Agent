package com.rentCar.service.impl;

import com.rentCar.model.TransmissionType;
import com.rentCar.repository.TransmissionTypeRepository;
import com.rentCar.service.TransmissionTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class TransTypeServiceImpl implements TransmissionTypeService {

    @Autowired
    private TransmissionTypeRepository transmissionTypeRepository;

    @Override
    public TransmissionType findOne(Long id) {
        return transmissionTypeRepository.findById(id).orElse(null);
}

    @Override
    public List<TransmissionType> findAll() {
        return transmissionTypeRepository.findAll();
    }
}
