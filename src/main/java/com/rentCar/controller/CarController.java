package com.rentCar.controller;

import com.rentCar.dto.CarDTO;
import com.rentCar.model.Car;
import com.rentCar.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "car")
@CrossOrigin("http://localhost:4200")
public class CarController {

    @Autowired
    private CarService carService;

    @GetMapping(value = "/{id}", produces = "application/json")
    //@PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> getCar(@PathVariable("id") String id) {

        try {
            Car car = this.carService.findById(id);
            CarDTO carDTO = new CarDTO(car);
            return new ResponseEntity(carDTO, HttpStatus.OK);

        } catch (NullPointerException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Can not find car");
        }
    }
}
