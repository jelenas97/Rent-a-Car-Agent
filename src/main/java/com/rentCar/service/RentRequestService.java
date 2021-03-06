package com.rentCar.service;

import com.rentCar.dto.RentRequestDTO;
import com.rentCar.dto.RequestsHolderDTO;
import com.rentCar.model.RentRequest;

import java.time.LocalDateTime;
import java.util.List;

public interface RentRequestService {

    List<RentRequestDTO> getHistoryRentRequests(long id);

    List<RentRequestDTO> getCancelableRentRequests(long id);

    void save(RentRequest requestDTO);

    void changeStatus(Long id, String status);

    List<RentRequest> findPending(Long id, LocalDateTime startDate, LocalDateTime endDate);

    RentRequestDTO cancelRentRequest(long id);

    RentRequest findById(long rentRequestId);

    List<RentRequestDTO> getRentRequestReserved(long id);

    void processRequest(String confirm, RentRequestDTO rentDTO);

    void processRequestsBundle(String confirm, RequestsHolderDTO holderDTO);

    void sendRequest(RequestsHolderDTO holderDTO);

    void rent(RentRequest rentRequest);

}
