package com.rentCar.controller;

import com.rentCar.model.FuelType;
import com.rentCar.service.impl.FuelTypeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(value = "fuel")
@CrossOrigin("http://localhost:4200")
public class FuelTypeController {
    @Autowired
    private FuelTypeServiceImpl fuelTypeService;

    @PostMapping(produces = "application/json", consumes = "application/json")
    //@PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity newFuel(@RequestBody String name) {

        try {
            FuelType fuelType = this.fuelTypeService.findOneByName(name);
            if (fuelType != null) {
                this.fuelTypeService.setActive(name);
            } else {
                this.fuelTypeService.save(name);
            }
            return new ResponseEntity(HttpStatus.OK);

        } catch (NullPointerException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping(value = "/{name}")
    //@PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity deleteFuel(@PathVariable String name) {

        try {
            FuelType fuelType = this.fuelTypeService.findOneByName(name);
            if (fuelType != null) {
                this.fuelTypeService.delete(name);
            }
        } catch (NullPointerException e) {
            return ResponseEntity.notFound().build();
        }
        return new ResponseEntity(HttpStatus.OK);
    }
}
