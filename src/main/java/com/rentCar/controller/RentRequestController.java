package com.rentCar.controller;

import com.rentCar.RentCar.wsdl.PhysicalRentResponse;
import com.rentCar.dto.RentRequestDTO;
import com.rentCar.dto.RequestsHolderDTO;
import com.rentCar.model.Advertisement;
import com.rentCar.model.RentRequest;
import com.rentCar.model.RequestsHolder;
import com.rentCar.model.User;
import com.rentCar.service.*;
import com.rentCar.soap.RentClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.PermitAll;
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

    @Autowired
    private RequestsHolderService requestsHolderService;

    @Autowired
    private TermService termService;



    @Autowired
    private RentClient rentClient;

    @PostMapping(produces = "application/json", consumes = "application/json")
    @PreAuthorize("hasAuthority('ROLE_AGENT') or hasAuthority('ROLE_CLIENT')")
    public ResponseEntity sentRequest(@RequestBody RequestsHolderDTO holderDTO) {
        try {
            this.rentRequestService.sendRequest(holderDTO);
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
    @PermitAll
    public ResponseEntity<?> processRequestsBundle(@PathVariable String confirm, @RequestBody RequestsHolderDTO holderDTO) {
        try {
            this.rentClient.processRequestsBundle(confirm, holderDTO);
            return new ResponseEntity(null, HttpStatus.OK);
        } catch (NullPointerException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Error during processing request bundle");
        }
    }

    @PostMapping(value = "/request/{confirm}", produces = "application/json")
    //   @PreAuthorize("hasAuthority('ROLE_AGENT')")
    @PermitAll
    public ResponseEntity<?> processRequest(@PathVariable String confirm, @RequestBody RentRequestDTO rentDTO) {
        try {
            this.rentClient.processRequest(confirm, rentDTO);
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
            User sender = this.userService.findById(rentDTO.getSenderId());
            Advertisement advertisement = this.advertisementService.find(rentDTO.getAdvertisementId());
            RequestsHolder holder = new RequestsHolder();
            holder.setBundle(false);
            RentRequest req1 = new RentRequest(rentDTO, sender, advertisement, holder);
            this.rentRequestService.save(req1);
            this.termService.save(rentDTO.getAdvertisementId(), rentDTO.getStartDateTime(), rentDTO.getEndDateTime());
            //SOOOAP!!!
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
