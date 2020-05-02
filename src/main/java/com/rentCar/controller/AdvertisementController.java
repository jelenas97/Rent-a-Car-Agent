package com.rentCar.controller;

import com.rentCar.dto.SearchDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "advertisement")
@CrossOrigin("http://localhost:4200")
public class AdvertisementController {

    @GetMapping(produces="application/json")
    // @PreAuthorize("hasRole('')")
    public ResponseEntity<?> getModels(@RequestBody SearchDTO searchDto){

        try {

            return new ResponseEntity(null, HttpStatus.OK);

        }catch(NullPointerException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Error during searching");
        }
    }

}
