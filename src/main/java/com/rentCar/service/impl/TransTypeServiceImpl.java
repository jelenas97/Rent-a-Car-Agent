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

    @Override
    public List<TransmissionType> findAllActive() {
        return transmissionTypeRepository.getActiveTransTypes();
    }

    @Override
    public TransmissionType findOneByName(String name) {
        return this.transmissionTypeRepository.findByName(name);
    }

    @Override
    public void save(String name) {
        this.transmissionTypeRepository.save(new TransmissionType(name));
    }

    @Override
    public void delete(TransmissionType transmissionType) {
        transmissionType.setActive(false);
        this.transmissionTypeRepository.save(transmissionType);

    }

    @Override
    public void setActive(String name) {
        TransmissionType transmissionType = this.transmissionTypeRepository.findByName(name);
        transmissionType.setActive(true);
        this.transmissionTypeRepository.save(transmissionType);
    }
}
