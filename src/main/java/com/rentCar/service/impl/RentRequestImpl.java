package com.rentCar.service.impl;

import com.rentCar.dto.RentRequestDTO;
import com.rentCar.enumerations.RentRequestStatus;
import com.rentCar.model.RentRequest;
import com.rentCar.repository.RentRequestRepository;
import com.rentCar.service.RentRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class RentRequestImpl implements RentRequestService {

    @Autowired
    private RentRequestRepository rentRequestRepository;

    @Override
    public List<RentRequestDTO> getHistoryRentRequests(long id) {

        List<RentRequestDTO> historyList = new ArrayList<>();

        LocalDateTime dateTime = LocalDateTime.now();
        RentRequestStatus status = RentRequestStatus.PAID;
        List<RentRequest> historyListR = rentRequestRepository.findByClientIdAndRentRequestStatusAndEndDateTimeGreaterThanEqual(id, status, dateTime);

        System.out.println(historyListR);
        for (RentRequest rr : historyListR) {
            historyList.add(new RentRequestDTO(rr));
        }

        System.out.println(historyList);
        return historyList;
    }

    @Override
    public List<RentRequestDTO> getCancelableRentRequests(long id) {

        List<RentRequestDTO> cancelableList = new ArrayList<>();

        List<RentRequestStatus> statuses = new ArrayList<>();
        statuses.add(RentRequestStatus.PENDING);
        statuses.add(RentRequestStatus.RESERVED);
        List<RentRequest> cancelableListR = rentRequestRepository.findByClientIdAndRentRequestStatusIn(id, statuses);

        System.out.println(cancelableListR);
        for (RentRequest rr : cancelableListR) {
            cancelableList.add(new RentRequestDTO(rr));
        }

        return cancelableList;
    }

}
