package com.rentCar.controller;

import com.rentCar.dto.RentRequestDTO;
import com.rentCar.dto.RequestsHolderDTO;
import com.rentCar.model.Advertisement;
import com.rentCar.model.RentRequest;
import com.rentCar.model.RequestsHolder;
import com.rentCar.model.User;
import com.rentCar.service.AdvertisementService;
import com.rentCar.service.RentRequestService;
import com.rentCar.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
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

//    @Autowired
//    privat



    @PostMapping(produces = "application/json", consumes = "application/json")
    //@PreAuthorize("hasRole('CLIENT') and hasRole('AGENT')")
    public ResponseEntity sentRequest(@RequestBody RequestsHolderDTO holderDTO) {

        try {
            System.out.println("Posal zahtjev " + holderDTO);
            System.out.println("Posal zahtjev " + holderDTO.toString());
            for (RentRequestDTO requestDTO : holderDTO.getRentRequests()) {
                Advertisement advertisement = this.advertisementService.find(requestDTO.getAdvertisementId());
                User sender = this.userService.find(requestDTO.getSenderId());
                RequestsHolder requestsHolder = new RequestsHolder();
                requestsHolder.setBundle(holderDTO.getBundle());
                RentRequest rentRequest = new RentRequest(requestDTO, sender, advertisement, requestsHolder);

                //treba i holder snimiti!!
                this.rentRequestService.save(rentRequest);

            }




//            User user = this.userService.findOne(requestDTO.getSender_email());
//            Set<Advertisement> advertisements = new HashSet<>();
//
//            for(AdvertisementDTO ad: requestDTO.getAdvertisements()){
//                advertisements.add(this.advertisementService.find(ad.getId()));
//            }
//
//            this.rentRequestService.save(new RentRequest(requestDTO,user,advertisements));

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

}
