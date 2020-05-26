package com.rentCar.controller;

import com.rentCar.model.CarBrand;
import com.rentCar.service.CarBrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(value = "brand")
@CrossOrigin("http://localhost:4200")
public class CarBrandController {
    @Autowired
    private CarBrandService carBrandService;

    @PostMapping(produces = "application/json", consumes = "application/json")
    @Secured("ROLE_ADMIN")
    public ResponseEntity<?> newBrand(@RequestBody String name) {

        try {
            CarBrand brand = this.carBrandService.findOneByName(name);
            if (brand != null) {
                this.carBrandService.setActive(name);
            } else {
                this.carBrandService.save(name);
            }
            return new ResponseEntity<>(HttpStatus.OK);

        } catch (NullPointerException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping(value = "/{id}")
    @Secured("ROLE_ADMIN")
    public ResponseEntity<?> deleteBrand(@PathVariable String id) {

        try {
            CarBrand brand = this.carBrandService.findOne(Long.parseLong(id));
            if (brand != null) {
                this.carBrandService.delete(brand);
            }
        } catch (NullPointerException e) {
            return ResponseEntity.notFound().build();
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
