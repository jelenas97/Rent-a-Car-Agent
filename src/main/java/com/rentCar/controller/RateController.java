package com.rentCar.controller;

import com.rentCar.dto.CommentDTO;
import com.rentCar.dto.RateDTO;
import com.rentCar.service.RateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(value = "rate")
@CrossOrigin("http://localhost:4200")
public class RateController {

    @Autowired
    RateService rateService;

    @PostMapping(produces = "application/json", consumes = "application/json")
    @PreAuthorize("hasAuthority('ROLE_CLIENT')")
    public ResponseEntity<?> rateAdvertisement(@RequestBody RateDTO dto) {

        try {
            long id = this.rateService.rateAdvertisement(dto);
            return new ResponseEntity(id, HttpStatus.CREATED);
        } catch (Exception e) {
            System.out.println(e);
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(value="/{id}", produces="application/json")
    @PreAuthorize("hasAnyAuthority('ROLE_CLIENT', 'ROLE_AGENT')")
    public ResponseEntity<?> getAverageAdvertisementRate(@PathVariable Long id){

        try {
            List<RateDTO> rates = this.rateService.findAverageAdvRate(id);

            return new ResponseEntity(rates, HttpStatus.OK);

        }catch(NullPointerException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Error while loading rates");
        }
    }
}
