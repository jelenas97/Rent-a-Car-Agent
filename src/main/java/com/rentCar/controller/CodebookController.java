package com.rentCar.controller;


import com.rentCar.dto.CodeBookDTO;
import com.rentCar.model.*;
import com.rentCar.service.impl.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping(value = "codebook")
@CrossOrigin("http://localhost:4200")
public class CodebookController {

    @Autowired
    private CarBrandServiceImpl carBrandService;
    @Autowired
    private CarModelServiceImpl carModelService;
    @Autowired
    private CarClassServiceImpl carClassService;
    @Autowired
    private FuelTypeServiceImpl fuelTypeService;
    @Autowired
    private TransTypeServiceImpl transTypeService;


    @GetMapping(value="/getCodeBookInfo", produces="application/json")
    //@PreAuthorize("hasRole('ADMIN')"
    public ResponseEntity<?> getCodeBookInfo()
    {
        try {
            List<String> carBrands = this.carBrandService.findAllStringList();
            List<String> carClasses = this.carClassService.findAllStringList();
            List<String> carModels = this.carModelService.findAllStringList();
            List<String> fuelTypes = this.fuelTypeService.findAllStringList();
            List<String> transmissionTypes = this.transTypeService.findAllStringList();
            CodeBookDTO codeBook = new  CodeBookDTO(carBrands,carClasses, carModels, fuelTypes,transmissionTypes);

            return new ResponseEntity(codeBook, HttpStatus.OK);

        } catch(NullPointerException e){

            return ResponseEntity.notFound().build();
        }
    }

}
