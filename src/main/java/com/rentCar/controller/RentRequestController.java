package com.rentCar.controller;

import com.rentCar.dto.RentRequestDTO;
import com.rentCar.dto.RequestsHolderDTO;
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



    @PostMapping(produces = "application/json", consumes = "application/json")
    //@PreAuthorize("hasRole('CLIENT') and hasRole('AGENT')")
    public ResponseEntity sentRequest(@RequestBody RequestsHolderDTO holderDTO) {

        try {
            System.out.println("Posal zahtjev " + holderDTO);
            System.out.println("Posal zahtjev " + holderDTO.toString());
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
