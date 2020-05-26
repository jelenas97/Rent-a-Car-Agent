package com.rentCar.controller;

import com.rentCar.model.CarClass;
import com.rentCar.service.CarClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(value = "class")
@CrossOrigin("http://localhost:4200")
public class CarClassController {
    @Autowired
    private CarClassService carClassService;

    @PostMapping(produces = "application/json", consumes = "application/json")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity newClass(@RequestBody String name) {

        try {
            CarClass carClass = this.carClassService.findOneByName(name);
            if (carClass != null) {
                this.carClassService.setActive(name);
            } else {
                this.carClassService.save(name);
            }
            return new ResponseEntity(HttpStatus.OK);

        } catch (NullPointerException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping(value = "/{name}")
    //@PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity deleteClass(@PathVariable String name) {

        try {
            CarClass carClass = this.carClassService.findOneByName(name);
            if (carClass != null) {
                this.carClassService.delete(name);
            }
        } catch (NullPointerException e) {
            return ResponseEntity.notFound().build();
        }
        return new ResponseEntity(HttpStatus.OK);
    }

}
