package com.rentCar.service.impl;

import com.rentCar.model.FuelType;
import com.rentCar.model.TransmissionType;
import com.rentCar.repository.TransmissionTypeRepository;
import com.rentCar.service.TransmissionTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
    public List<String> findAllStringList()
    {
        List<String> stringList = new ArrayList<>();
        for(TransmissionType transmissionType: transmissionTypeRepository.findAll()){
            stringList.add(transmissionType.getName());
        }
        return stringList;
    }
}
