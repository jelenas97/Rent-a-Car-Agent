package com.rentCar.service;

import com.rentCar.dto.RequestsHolderDTO;
import com.rentCar.model.RequestsHolder;

import java.util.List;

public interface RequestsHolderService {
    void save(RequestsHolder requestsHolder);

    List<RequestsHolderDTO> getAllPending(Long id);
}
