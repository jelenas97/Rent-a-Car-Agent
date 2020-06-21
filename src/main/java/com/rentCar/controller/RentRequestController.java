package com.rentCar.controller;

import com.rentCar.RentCar.wsdl.PhysicalRentResponse;
import com.rentCar.dto.RentRequestDTO;
import com.rentCar.dto.RequestsHolderDTO;
import com.rentCar.model.*;
import com.rentCar.service.*;
import com.rentCar.soap.RentClient;
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



    @Autowired
    private RentClient rentClient;

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
                        User sender = this.userService.findById(requestDTO.getSenderId());

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
    @PreAuthorize("hasAuthority('ROLE_CLIENT')")
    public ResponseEntity<List<RentRequestDTO>> getHistoryRentRequests(@PathVariable String id) {

        try {
            return new ResponseEntity<>(rentRequestService.getHistoryRentRequests(Long.parseLong(id)), HttpStatus.OK);
        } catch (Exception e) {
            System.out.println(e);
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = "/cancelable/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAuthority('ROLE_CLIENT')")
    public ResponseEntity<List<RentRequestDTO>> getCancelableRentRequests(@PathVariable String id) {

        try {
            return new ResponseEntity<>(rentRequestService.getCancelableRentRequests(Long.parseLong(id)), HttpStatus.OK);
        } catch (Exception e) {
            System.out.println(e);
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = "/user/{id}/reserved", produces = MediaType.APPLICATION_JSON_VALUE)
    //@PreAuthorize("hasAuthority('ROLE_CLIENT')")
    public ResponseEntity<List<RentRequestDTO>> getRentRequestsReserved(@PathVariable String id) {

        try {
            return rentClient.getRentRequests(Long.parseLong(id));
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

    @PostMapping(value = "/physicalRent", produces = "application/json")
    @PreAuthorize("hasAuthority('ROLE_AGENT')")
    public ResponseEntity<?> physicalRent(@RequestBody RentRequestDTO rentDTO) {

        try {
            System.out.println("Physical rent " + rentDTO);

            //   this.rentRequestService.changeStatus(rentDTO.getId(), "PAID");
            this.termService.save(rentDTO.getAdvertisementId(), rentDTO.getStartDateTime(), rentDTO.getEndDateTime());

            PhysicalRentResponse response = rentClient.physicalRent(rentDTO);
            //automatsko odbijanje

            List<RentRequest> rentRequests = this.rentRequestService.findPending(rentDTO.getAdvertisementId(), rentDTO.getStartDateTime(), rentDTO.getEndDateTime());
            System.out.println("OVI SU VEC POSTOJALI: " + rentRequests);


            for (RentRequest request : rentRequests) {
                if (request.getRequests().getBundle()) {
                    List<RequestsHolderDTO> holders = this.requestsHolderService.getAllPending(request.getAdvertisement().getOwner().getId());

                    System.out.println("Postojali su holderi : " + holders);
                    for (RequestsHolderDTO hold : holders) {
                        System.out.println("Postoji toliko request u holderu : " + hold);
                        for (RentRequestDTO holderRentRequest : hold.getRentRequests()) {
                            System.out.println("Ovo je bilo u bundle uklanjam!!!" + holderRentRequest.getId());
                            this.rentRequestService.changeStatus(holderRentRequest.getId(), "CANCELED");


                        }
                    }

                } else {
                    this.rentRequestService.changeStatus(request.getId(), "CANCELED");
                }

            }

            return new ResponseEntity(null, HttpStatus.OK);
        } catch (NullPointerException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Error during processing request bundle");
        }
    }

    @PutMapping("/cancel/{id}")
    @PreAuthorize("hasAuthority('ROLE_CLIENT')")
    public ResponseEntity cancelRentRequest(@PathVariable long id){

        try{
            RentRequestDTO rrDTO = rentRequestService.cancelRentRequest(id);
            return  new ResponseEntity(rrDTO, HttpStatus.OK);
        }catch(Exception e){
                return new ResponseEntity(e.getMessage(), HttpStatus.CONFLICT);
        }
    }

}
