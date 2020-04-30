package com.rentCar.controller;

import com.rentCar.model.CarModel;
import com.rentCar.service.impl.CarModelServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(value = "model")
@CrossOrigin("http://localhost:4200")
public class CarModelController {
    @Autowired
    private CarModelServiceImpl carModelService;

    @PostMapping(produces = "application/json", consumes = "application/json")
    //@PreAuthorize("hasRole('")
    public ResponseEntity newModel(@RequestBody String name) {

        try {
            CarModel carModel = this.carModelService.findOneByName(name);
            if (carModel != null) {
                this.carModelService.setActive(name);
            } else {
                this.carModelService.addModel(name);
            }
            return new ResponseEntity(HttpStatus.OK);

        } catch (NullPointerException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping(value = "/{name}")
    public ResponseEntity deleteModel(@PathVariable String name) {

        try {
            CarModel carModel = this.carModelService.findOneByName(name);
            if (carModel != null) {
                this.carModelService.deleteModel(name);
            }
        } catch (NullPointerException e) {
            return ResponseEntity.notFound().build();
        }
        return new ResponseEntity(HttpStatus.OK);
    }
}
