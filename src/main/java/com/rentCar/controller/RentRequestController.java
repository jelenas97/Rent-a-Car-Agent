package com.rentCar.controller;

import com.rentCar.dto.RentRequestDTO;
import com.rentCar.dto.RequestsHolderDTO;
import com.rentCar.model.*;
import com.rentCar.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
@RequestMapping(value = "rentRequest")
@CrossOrigin("http://localhost:4200")
public class RentRequestController {

    @Autowired
    private RentRequestService rentRequestService;

    @Autowired
    private UserService userService;

    @Autowired
    private AdvertisementService advertisementService;

    @Autowired
    private RequestsHolderService requestsHolderService;

    @Autowired
    private TermService termService;

    @PostMapping(produces = "application/json", consumes = "application/json")
    //@PreAuthorize("hasRole('CLIENT') and hasRole('AGENT')")
    public ResponseEntity sentRequest(@RequestBody RequestsHolderDTO holderDTO) {

        try {
            System.out.println("Posal zahtjev " + holderDTO);

            Set<Long> usersIds = new HashSet<>();
            for (RentRequestDTO requestDTO : holderDTO.getRentRequests()) {
                Advertisement advertisement = this.advertisementService.find(requestDTO.getAdvertisementId());
                usersIds.add(advertisement.getOwner().getId());
            }
            for (Long id : usersIds) {
                RequestsHolder rq = new RequestsHolder(holderDTO.getBundle());
                System.out.println("Vlasnik = " + id);
                for (RentRequestDTO requestDTO : holderDTO.getRentRequests()) {

                    Advertisement advertisement = this.advertisementService.find(requestDTO.getAdvertisementId());
                    if (id.equals(advertisement.getOwner().getId())) {
                        User sender = this.userService.find(requestDTO.getSenderId());

                        RentRequest rentRequest = new RentRequest(requestDTO, sender, advertisement, rq);
                        System.out.println(rentRequest);
                        //treba i holder snimiti!!
                        this.rentRequestService.save(rentRequest);
                    }
                }
            }

            return new ResponseEntity(HttpStatus.OK);

        } catch (NullPointerException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping(value = "/history/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    //@PreAuthorize("hasRole('CLIENT')")
    public ResponseEntity<List<RentRequestDTO>> getHistoryRentRequests(@PathVariable String id) {

        try {
            return new ResponseEntity<>(rentRequestService.getHistoryRentRequests(Long.parseLong(id)), HttpStatus.OK);
        } catch (Exception e) {
            System.out.println(e);
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = "/cancelable/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    //@PreAuthorize("hasRole('CLIENT')")
    public ResponseEntity<List<RentRequestDTO>> getCancelableRentRequests(@PathVariable String id) {

        try {
            return new ResponseEntity<>(rentRequestService.getCancelableRentRequests(Long.parseLong(id)), HttpStatus.OK);
        } catch (Exception e) {
            System.out.println(e);
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping(value = "/bundle/{confirm}", produces = "application/json")
    // @PreAuthorize("hasRole('AGENT')")
    public ResponseEntity<?> processRequestsBundle(@PathVariable String confirm, @RequestBody RequestsHolderDTO holderDTO) {

        try {
            if (confirm.equals("YES")) {
                //true = nema preklapanja  u jednom terminu! Dodaj ih sve!
                //false = ima preklapanja u jednom/vise! Sve odbij!
                Boolean yes = true;
                for (RentRequestDTO rentDTO : holderDTO.getRentRequests()) {
                    List<Term> term = this.termService.findTakenTerm(rentDTO.getAdvertisementId(), rentDTO.getStartDateTime(), rentDTO.getEndDateTime());
                    if (term.size() != 0) {
                        yes = false;
                    }
                }
                if (yes) {
                    for (RentRequestDTO rentDTO : holderDTO.getRentRequests()) {
                        this.rentRequestService.changeStatus(rentDTO.getId(), "RESERVED");
                        this.termService.save(rentDTO.getAdvertisementId(), rentDTO.getStartDateTime(), rentDTO.getEndDateTime());
                    }
                } else {
                    for (RentRequestDTO rentDTO : holderDTO.getRentRequests()) {
                        this.rentRequestService.changeStatus(rentDTO.getId(), "CANCELED");
                    }
                }
            } else {
                for (RentRequestDTO r : holderDTO.getRentRequests()) {
                    this.rentRequestService.changeStatus(r.getId(), "CANCELED");
                }

            }

            return new ResponseEntity(null, HttpStatus.OK);
        } catch (NullPointerException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Error during processing request bundle");
        }
    }

    @PostMapping(value = "/request/{confirm}", produces = "application/json")
    // @PreAuthorize("hasRole('')")
    public ResponseEntity<?> processRequest(@PathVariable String confirm, @RequestBody RentRequestDTO rentDTO) {

        try {
            if (confirm.equals("YES")) {
                System.out.println(rentDTO);
                List<Term> term = this.termService.findTakenTerm(rentDTO.getAdvertisementId(), rentDTO.getStartDateTime(), rentDTO.getEndDateTime());
                if (term.size() == 0) {
                    System.out.println("NEMA TERMINA SA PREKLAPANJEM!!!!");
                    this.rentRequestService.changeStatus(rentDTO.getId(), "RESERVED");
                    //POSLOVNA INFORMATIKA??? -> RESERVED -> PAID !! Ako se odbije onda status TERM (canceled ide u TRUE)!!!
                    this.termService.save(rentDTO.getAdvertisementId(), rentDTO.getStartDateTime(), rentDTO.getEndDateTime());
                } else {
                    System.out.println(term.size());
                    this.rentRequestService.changeStatus(rentDTO.getId(), "CANCELED");
                }

            } else {
                this.rentRequestService.changeStatus(rentDTO.getId(), "CANCELED");

            }

            return new ResponseEntity(null, HttpStatus.OK);
        } catch (NullPointerException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Error during processing request bundle");
        }
    }

    @PostMapping(value = "/physical", produces = "application/json")
    @PreAuthorize("hasRole('AGENT')")
    public ResponseEntity<?> physicalRent(@RequestBody RentRequestDTO rentDTO) {

        try {
            this.rentRequestService.changeStatus(rentDTO.getId(), "PAID");
            this.termService.save(rentDTO.getAdvertisementId(), rentDTO.getStartDateTime(), rentDTO.getEndDateTime());

            //automatsko odbijanje

            List<RentRequest> rentRequests = this.rentRequestService.findPending(rentDTO.getId(), rentDTO.getStartDateTime(), rentDTO.getEndDateTime());
            for (RentRequest request : rentRequests) {
                this.rentRequestService.changeStatus(rentDTO.getId(), "CANCELED");
            }

            return new ResponseEntity(null, HttpStatus.OK);
        } catch (NullPointerException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Error during processing request bundle");
        }
    }


}
