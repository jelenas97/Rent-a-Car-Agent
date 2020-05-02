package com.rentCar.controller;


import com.rentCar.model.TransmissionType;
import com.rentCar.service.impl.TransTypeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(value = "transmission")
@CrossOrigin("http://localhost:4200")
public class TransmissionController {

    @Autowired
    private TransTypeServiceImpl transTypeService;

    @PostMapping(produces = "application/json", consumes = "application/json")
    //@PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity newTransmission(@RequestBody String name) {

        try {
            TransmissionType transmissionType = this.transTypeService.findOneByName(name);
            if (transmissionType != null) {
                this.transTypeService.setActive(name);
            } else {
                this.transTypeService.addTransmission(name);
            }
            return new ResponseEntity(HttpStatus.OK);

        } catch (NullPointerException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping(value = "/{name}")
    //@PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity deleteTransmission(@PathVariable String name) {

        try {
            TransmissionType transmissionType = this.transTypeService.findOneByName(name);
            if (transmissionType != null) {
                this.transTypeService.deleteTransmission(name);
            }
        } catch (NullPointerException e) {
            return ResponseEntity.notFound().build();
        }
        return new ResponseEntity(HttpStatus.OK);
    }
}
