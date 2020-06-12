package com.rentCar.controller;


import com.rentCar.model.TransmissionType;
import com.rentCar.service.TransmissionTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(value = "transmission")
@CrossOrigin("http://localhost:4200")
public class TransmissionController {

    @Autowired
    private TransmissionTypeService transTypeService;

    @PostMapping(produces = "application/json", consumes = "application/json")
    @Secured("ROLE_ADMIN")
    public ResponseEntity<?> newTransmission(@RequestBody String name) {

        try {
            TransmissionType transmissionType = this.transTypeService.findOneByName(name);
            if (transmissionType != null) {
                this.transTypeService.setActive(name);
            } else {
                this.transTypeService.save(name);
            }
            return new ResponseEntity<>(HttpStatus.OK);

        } catch (NullPointerException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping(value = "/{id}")
    @Secured("ROLE_ADMIN")
    public ResponseEntity<?> deleteTransmission(@PathVariable String id) {

        try {
            TransmissionType transmissionType = this.transTypeService.findOne(Long.parseLong(id));
            if (transmissionType != null) {
                this.transTypeService.delete(transmissionType);
            }
        } catch (NullPointerException e) {
            return ResponseEntity.notFound().build();
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
