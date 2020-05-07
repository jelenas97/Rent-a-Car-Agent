package com.rentCar.controller;

import com.rentCar.dto.AdvertisementDTO;
import com.rentCar.model.Advertisement;
import com.rentCar.model.RentRequest;
import com.rentCar.model.User;
import com.rentCar.service.AdvertisementService;
import com.rentCar.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashSet;
import java.util.Set;

@Controller
@RequestMapping(value = "rentRequests")
@CrossOrigin("http://localhost:4200")
public class RentRequestController {

    @Autowired
    private RentRequestService rentRequestService;

    @Autowired
    private UserService userService;

    @Autowired
    private AdvertisementService advertisementService;



    @PostMapping(produces = "application/json", consumes = "application/json")
    //@PreAuthorize("hasRole('CLIENT') and hasRole('AGENT')")
    public ResponseEntity sentRequest(@RequestBody RentRequestDTO requestDTO) {

        try {

            User user = this.userService.findOne(requestDTO.getSender_email());
            Set<Advertisement> advertisements = new HashSet<>();

            for(AdvertisementDTO ad: requestDTO.getAdvertisements()){
                advertisements.add(this.advertisementService.find(ad.getId()));
            }

            this.rentRequestService.save(new RentRequest(requestDTO,user,advertisements));

            return new ResponseEntity(HttpStatus.OK);

        } catch (NullPointerException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
