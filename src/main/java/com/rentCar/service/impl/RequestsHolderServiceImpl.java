package com.rentCar.service.impl;

import com.rentCar.model.RequestsHolder;
import com.rentCar.repository.RequestsHolderRepository;
import com.rentCar.service.RequestsHolderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RequestsHolderServiceImpl implements RequestsHolderService {

    @Autowired
    private RequestsHolderRepository requestsHolderRepository;

    @Override
    public void save(RequestsHolder requestsHolder) {
        this.requestsHolderRepository.save(requestsHolder);
    }
}
