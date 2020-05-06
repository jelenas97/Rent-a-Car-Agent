package com.rentCar.service;

import com.rentCar.dto.RentRequestDTO;
import org.springframework.stereotype.Service;

import java.util.List;

public interface RentRequestService {

    List<RentRequestDTO> getHistoryRentRequests(long id);
    List<RentRequestDTO> getCancelableRentRequests(long id);
}
