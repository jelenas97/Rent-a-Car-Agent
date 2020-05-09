package com.rentCar.controller;

import com.rentCar.dto.RentRequestDTO;
import com.rentCar.dto.RequestsHolderDTO;
import com.rentCar.model.Advertisement;
import com.rentCar.model.RentRequest;
import com.rentCar.model.RequestsHolder;
import com.rentCar.model.User;
import com.rentCar.service.AdvertisementService;
import com.rentCar.service.RentRequestService;
import com.rentCar.service.RequestsHolderService;
import com.rentCar.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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

//    @Autowired
//    privat


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
    // @PreAuthorize("hasRole('')")
    public ResponseEntity<?> processRequestsBundle(@PathVariable String confirm, @RequestBody RequestsHolderDTO holderDTO) {

        try {
            if (confirm.equals("YES")) {
                for (RentRequestDTO rentRequestDTO : holderDTO.getRentRequests()) {
                    System.out.println(rentRequestDTO.getId());
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


            } else {
                this.rentRequestService.changeStatus(rentDTO.getId(), "CANCELED");

            }

            return new ResponseEntity(null, HttpStatus.OK);
        } catch (NullPointerException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Error during processing request bundle");
        }
    }


}
