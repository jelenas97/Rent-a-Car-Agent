package com.rentCar.service.impl;

import com.rentCar.model.RentRequest;
import com.rentCar.repository.RentRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RentRequestServiceImpl implements RentRequestService {

    @Autowired
    private RentRequestRepository rentRequestRepository;

    @Override
    public void save(RentRequest rentRequest) {
        this.rentRequestRepository.save(rentRequest);
    }
}
