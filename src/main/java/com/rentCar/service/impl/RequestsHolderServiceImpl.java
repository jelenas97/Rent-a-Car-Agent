package com.rentCar.service.impl;

import com.rentCar.dto.RequestsHolderDTO;
import com.rentCar.model.RequestsHolder;
import com.rentCar.repository.RequestsHolderRepository;
import com.rentCar.service.RequestsHolderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RequestsHolderServiceImpl implements RequestsHolderService {

    @Autowired
    private RequestsHolderRepository requestsHolderRepository;

    @Override
    public void save(RequestsHolder requestsHolder) {
        this.requestsHolderRepository.save(requestsHolder);
    }

    @Override
    public List<RequestsHolderDTO> getAllPending(Long id) {

        List<RequestsHolderDTO> requestsHolderDTOS = new ArrayList<>();
        for (RequestsHolder req : this.requestsHolderRepository.getAllPending(id)) {
            requestsHolderDTOS.add(new RequestsHolderDTO(req));
        }
        return requestsHolderDTOS;
    }
}
