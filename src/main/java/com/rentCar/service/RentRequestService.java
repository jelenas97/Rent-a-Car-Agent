package com.rentCar.service;

import com.rentCar.dto.RentRequestDTO;

import java.util.List;

public interface RentRequestService {

    List<RentRequestDTO> getHistoryRentRequests(long id);

    List<RentRequestDTO> getCancelableRentRequests(long id);
}
