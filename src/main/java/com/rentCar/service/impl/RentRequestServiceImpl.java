package com.rentCar.service.impl;

import com.rentCar.dto.EmailMessage;
import com.rentCar.dto.RentRequestDTO;
import com.rentCar.dto.RequestsHolderDTO;
import com.rentCar.enumerations.RentRequestStatus;
import com.rentCar.model.*;
import com.rentCar.repository.CommentRepository;
import com.rentCar.repository.RateRepository;
import com.rentCar.repository.RentRequestRepository;
import com.rentCar.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class RentRequestServiceImpl implements RentRequestService {

    @Autowired
    private RentRequestRepository rentRequestRepository;

    @Autowired
    private RateRepository rateRepository;

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private TermService termService;

    @Autowired
    private AdvertisementService advertisementService;

    @Autowired
    private UserService userService;

    @Autowired
    private RequestsHolderService requestsHolderService;

    @Autowired
    private EmailService emailService;

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

    @Override
    public void rent(RentRequest rentRequest) {
        if (rentRequest.getRentRequestStatus().equals(RentRequestStatus.PENDING)) {
            rentRequest.setRentRequestStatus(RentRequestStatus.RESERVED);
            this.termService.save(rentRequest.getAdvertisement().getId(), rentRequest.getStartDateTime(), rentRequest.getEndDateTime());
            this.save(rentRequest);
        }
    }


    @Override
    public void processRequest(String confirm, RentRequestDTO rentDTO) {
        // EmailMessage = new EmailMessage("")
        RentRequest request = this.rentRequestRepository.findById(rentDTO.getId()).orElse(null);

        String email = request.getSender().getEmail();
        String rejection = "Your request for reservation" + request.getAdvertisement().getCar().getCarClass() + "has been rejected";

        String accept = "Your request for reservation" + request.getAdvertisement().getCar().getCarClass() + "has been accepted";
        System.out.println(email);
        if (confirm.equals("YES")) {
            System.out.println(rentDTO);
            List<Term> term = this.termService.findTakenTerm(rentDTO.getAdvertisementId(), rentDTO.getStartDateTime(), rentDTO.getEndDateTime());
            System.out.println("Zauzeti termini su " + term.toString());

            if (term.size() == 0) {
                System.out.println("NEMA TERMINA SA PREKLAPANJEM!!!!");
                if (request != null) {
                    this.rent(request);

                    List<RentRequest> rentRequests = this.findPending(rentDTO.getAdvertisementId(), rentDTO.getStartDateTime(), rentDTO.getEndDateTime());
                    System.out.println("Ove odbijam " + rentRequests);
                    this.emailService.sendEmail(new EmailMessage(email, accept));
                    this.automaticRejection(rentRequests);
                }
            } else {
                this.emailService.sendEmail(new EmailMessage(email, rejection));
                this.changeStatus(rentDTO.getId(), RentRequestStatus.CANCELED.toString());
            }
        } else {
            this.emailService.sendEmail(new EmailMessage(email, rejection));
            this.changeStatus(rentDTO.getId(), RentRequestStatus.CANCELED.toString());
        }
    }

    @Override
    public void processRequestsBundle(String confirm, RequestsHolderDTO holderDTO) {
        String email = "";
        String rejection = "Your request for bundle has been rejected";

        String accept = "Your request for bundle has been accepted";
        RentRequestDTO dto = new RentRequestDTO();
        if (confirm.equals("YES")) {
            //true = nema preklapanja  u jednom terminu! Dodaj ih sve!
            //false = ima preklapanja u jednom/vise! Sve odbij!
            Boolean yes = true;
            for (RentRequestDTO rentDTO : holderDTO.getRentRequests()) {
                System.out.println(rentDTO);
                List<Term> term = this.termService.findTakenTerm(rentDTO.getAdvertisementId(), rentDTO.getStartDateTime(), rentDTO.getEndDateTime());
                System.out.println("Zauzeti termini su " + term.toString());
                if (term.size() != 0) {
                    yes = false;
                }
            }
            if (yes) {

                for (RentRequestDTO rentDTO : holderDTO.getRentRequests()) {
                    RentRequest request = this.rentRequestRepository.findById(rentDTO.getId()).orElse(null);
                    dto = rentDTO;
                    if (request != null) {
                        this.rent(request);
                    }
                }

                email = this.userService.findById(dto.getSenderId()).getEmail();
                this.emailService.sendEmail(new EmailMessage(email, accept));

                List<RentRequest> rentRequests = this.findPending(dto.getAdvertisementId(), dto.getStartDateTime(), dto.getEndDateTime());
                this.automaticRejection(rentRequests);
            } else {
                for (RentRequestDTO rentDTO : holderDTO.getRentRequests()) {
                    this.changeStatus(rentDTO.getId(), RentRequestStatus.CANCELED.toString());
                    dto = rentDTO;
                }
                email = this.userService.findById(dto.getSenderId()).getEmail();
                this.emailService.sendEmail(new EmailMessage(email, rejection));
            }
        } else {
            for (RentRequestDTO r : holderDTO.getRentRequests()) {
                this.changeStatus(r.getId(), RentRequestStatus.CANCELED.toString());
            }
            email = this.userService.findById(dto.getSenderId()).getEmail();
            this.emailService.sendEmail(new EmailMessage(email, rejection));
        }
    }

    @Override
    public void sendRequest(RequestsHolderDTO holderDTO) {
        System.out.println("Posal zahtjev " + holderDTO);
        Set<Long> usersIds = new HashSet<>();
        for (RentRequestDTO requestDTO : holderDTO.getRentRequests()) {
            Advertisement advertisement = this.advertisementService.find(requestDTO.getAdvertisementId());
            usersIds.add(advertisement.getOwner().getId());
        }
        System.out.println("Owners:" + usersIds);
        for (Long id : usersIds) {
            RequestsHolder rq = new RequestsHolder(holderDTO.getBundle());
            System.out.println("Vlasnik = " + id);

            for (RentRequestDTO requestDTO : holderDTO.getRentRequests()) {
                Advertisement advertisement = this.advertisementService.find(requestDTO.getAdvertisementId());

                if (id.equals(advertisement.getOwner().getId())) {
                    User sender = this.userService.findById(requestDTO.getSenderId());

                    RentRequest rentRequest = new RentRequest(requestDTO, sender, advertisement, rq);
                    System.out.println(rentRequest);
                    //treba i holder snimiti!!
                    this.save(rentRequest);
                }
            }
        }
    }


    public void automaticRejection(List<RentRequest> rentRequests) {
        for (RentRequest request : rentRequests) {
            RequestsHolder holder = this.requestsHolderService.findById(request.getRequests().getId());
            if (holder.getBundle()) {
                List<Long> listIds = holder.getRentRequests().stream()
                        .map(RentRequest::getId)
                        .collect(Collectors.toList());
                for (Long id : listIds) {
                    System.out.println("Ovo je bilo u bundle uklanjam!!!" + id);
                    this.changeStatus(id, "CANCELED");
                }
                String email = request.getSender().getEmail();
                String rejection = "Your request for bundle has been rejected";
                this.emailService.sendEmail(new EmailMessage(email, rejection));

            } else {
                this.changeStatus(request.getId(), "CANCELED");
                String email = request.getSender().getEmail();
                String rejection = "Your request for reservation" + request.getAdvertisement().getCar().getCarClass() + "has been rejected";
                this.emailService.sendEmail(new EmailMessage(email, rejection));
            }
        }
    }
}
