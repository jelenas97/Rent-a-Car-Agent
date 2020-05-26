package com.rentCar.controller;

import com.rentCar.service.TermService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "terms")
@CrossOrigin("http://localhost:4200")
public class TermController {


    @Autowired
    private TermService termService;

    @PostMapping(produces = "application/json")
    // @PreAuthorize("hasRole('')")
    public ResponseEntity<?> newTerm() {

        try {
            return new ResponseEntity(null, HttpStatus.OK);
        } catch (NullPointerException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Error during searching");
        }
    }

}
