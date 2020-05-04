package com.rentCar.controller;

import com.rentCar.model.CarBrand;
import com.rentCar.model.CarModel;
import com.rentCar.service.CarBrandService;
import com.rentCar.service.CarModelService;
import com.rentCar.service.impl.CarBrandServiceImpl;
import com.rentCar.service.impl.CarModelServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(value = "model")
@CrossOrigin("http://localhost:4200")
public class CarModelController {
    @Autowired
    private CarModelService carModelService;
    @Autowired
    private CarBrandService carBrandService;

    @PostMapping(value = "/{brand}",produces = "application/json", consumes = "application/json")
    //@PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity newModel(@RequestBody String name,@PathVariable String brand) {

        try {
            CarModel carModel = this.carModelService.findOneByName(name);
            CarBrand carBrand = this.carBrandService.findOneByName(brand);
            if (carModel != null) {
                this.carModelService.setActive(name);
            } else {
                this.carModelService.save(name,carBrand);
            }
            return new ResponseEntity(HttpStatus.OK);

        } catch (NullPointerException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping(value = "/{name}")
    //@PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity deleteModel(@PathVariable String name) {

        try {
            CarModel carModel = this.carModelService.findOneByName(name);
            if (carModel != null) {
                this.carModelService.delete(name);
            }
        } catch (NullPointerException e) {
            return ResponseEntity.notFound().build();
        }
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping(value="/{brand}", produces="application/json")
    //@PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> getModels(@PathVariable("brand") String brand){

        try {
            CarBrand carBrand =  this.carBrandService.findOneByName(brand);
            List<String> list = this.carModelService.findAllStringList(carBrand.getId());
            System.out.println(carBrand);
            System.out.println(list);
            return new ResponseEntity(list, HttpStatus.OK);

        }catch(NullPointerException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Missing brand name");
        }
    }

}
