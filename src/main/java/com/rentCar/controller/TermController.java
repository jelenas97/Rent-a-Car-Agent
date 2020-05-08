package com.rentCar.controller;

import com.rentCar.model.Term;
import com.rentCar.service.AdvertisementService;
import com.rentCar.service.TermService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDate;

@Controller
@RequestMapping(value = "terms")
@CrossOrigin("http://localhost:4200")
public class TermController {


    @Autowired
    private TermService termService;

    @Autowired
    private AdvertisementService advertisementService;

    @PostMapping(produces = "application/json")
    // @PreAuthorize("hasRole('')")
    public ResponseEntity<?> newTerm() {

        try {
            System.out.println("DSDSSD");

            LocalDate startDate = LocalDate.of(2020, 5, 6);
            LocalDate endDate = LocalDate.of(2020, 5, 16);
            LocalDate startDate2 = LocalDate.of(2020, 5, 21);
            LocalDate endDate2 = LocalDate.of(2020, 5, 23);
            this.termService.save(new Term(startDate, endDate, this.advertisementService.find(1L)));
            this.termService.save(new Term(startDate2, endDate2, this.advertisementService.find(1L)));

            return new ResponseEntity(null, HttpStatus.OK);
//select * from advertisement inner join term on advertisement.id = term.advertisement_id where period
        } catch (NullPointerException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Error during searching");
        }
    }
}
