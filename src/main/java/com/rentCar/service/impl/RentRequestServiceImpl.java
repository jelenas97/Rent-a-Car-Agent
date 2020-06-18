package com.rentCar.service.impl;

import com.rentCar.dto.RentRequestDTO;
import com.rentCar.enumerations.RentRequestStatus;
import com.rentCar.model.Comment;
import com.rentCar.model.Message;
import com.rentCar.model.Rate;
import com.rentCar.model.RentRequest;
import com.rentCar.repository.CommentRepository;
import com.rentCar.repository.RateRepository;
import com.rentCar.repository.RentRequestRepository;
import com.rentCar.service.RentRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class RentRequestServiceImpl implements RentRequestService {

    @Autowired
    private RentRequestRepository rentRequestRepository;

    @Autowired
    private RateRepository rateRepository;

    @Autowired
    private CommentRepository commentRepository;

    @Override
    public List<RentRequestDTO> getHistoryRentRequests(long id) {

        List<RentRequestDTO> historyList = new ArrayList<>();

        LocalDateTime dateTime = LocalDateTime.now();
        RentRequestStatus status = RentRequestStatus.PAID;
        List<RentRequest> historyListR = rentRequestRepository.findBySenderIdAndRentRequestStatusAndEndDateTimeLessThanEqual(id, status, dateTime);

        List<Rate> rates =rateRepository.findByClientId(id);
        List<Comment> comments = commentRepository.findByUserId(id);

        System.out.println(historyListR);
        for (RentRequest rr : historyListR) {

            historyList.add(new RentRequestDTO(rr, rates, comments));
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
        List<RentRequest> cancelableListR = rentRequestRepository.findBySenderIdAndRentRequestStatusIn(id, statuses);

        System.out.println(cancelableListR);
        for (RentRequest rr : cancelableListR) {
            cancelableList.add(new RentRequestDTO(rr, 0));
        }

        return cancelableList;
    }

    @Override
    public void save(RentRequest rentRequest) {
        this.rentRequestRepository.save(rentRequest);
    }

    @Override
    public void changeStatus(Long id, String status) {
        RentRequest rentRequest = this.rentRequestRepository.find(id);
        rentRequest.setRentRequestStatus(RentRequestStatus.valueOf(status));
        this.rentRequestRepository.save(rentRequest);
    }

    @Override
    public List<RentRequest> findPending(Long id, LocalDateTime startDate, LocalDateTime endDate) {
        return this.rentRequestRepository.findPending(id, startDate, endDate);
    }

    @Override
    public RentRequestDTO cancelRentRequest(long id) {

        RentRequest rr = this.rentRequestRepository.find(id);
        rr.setRentRequestStatus(RentRequestStatus.CANCELED);
        this.rentRequestRepository.save(rr);
        RentRequestDTO rrDTO = new RentRequestDTO(rr, 0);

        return rrDTO;
    }

    @Override
    public RentRequest findById(long rentRequestId) {
        return this.rentRequestRepository.findById(rentRequestId).orElse(null);
    }

    @Override
    public List<RentRequestDTO> getRentRequestReserved(long id) {
        List<RentRequestDTO> reserved = new ArrayList<>();

        List<RentRequest> listReserved = this.rentRequestRepository.findBySenderIdAndStatus(id);
        listReserved.addAll(this.rentRequestRepository.findByOwnerIdAndStatus(id));
        for (RentRequest rr : listReserved) {
            int numberOfUnseen = 0;
            for (Message m : rr.getMessages()) {
                if (m.getRecepient().getId().equals(id) && m.getSeen().equals(false)) {
                    numberOfUnseen++;
                }
            }
            reserved.add(new RentRequestDTO(rr, numberOfUnseen));
        }

        return reserved;
    }


}
