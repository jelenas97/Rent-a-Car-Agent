package com.rentCar.service;

import com.rentCar.model.RegisterRequest;

import java.util.List;

public interface RegisterRequestService {
    void save(RegisterRequest registerRequest);

    void delete(RegisterRequest registerRequest);

    List<RegisterRequest> getAll();
}
