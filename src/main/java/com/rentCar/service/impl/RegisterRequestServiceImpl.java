package com.rentCar.service.impl;

import com.rentCar.model.RegisterRequest;
import com.rentCar.repository.RegisterRequestRepository;
import com.rentCar.service.RegisterRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RegisterRequestServiceImpl implements RegisterRequestService {

    @Autowired
    private RegisterRequestRepository registerRequestRepository;

    @Override
    public void save(RegisterRequest registerRequest) {
        registerRequestRepository.save(registerRequest);
    }

    @Override
    public void delete(RegisterRequest registerRequest) {
        registerRequestRepository.delete(registerRequest);
    }

    @Override
    public List<RegisterRequest> getAll() {
        return registerRequestRepository.findAll();
    }
}
