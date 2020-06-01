package com.rentCar.controller;


import com.rentCar.dto.CodeBookDTO;
import com.rentCar.dto.CodeBookModelDTO;
import com.rentCar.model.CarBrand;
import com.rentCar.model.CarClass;
import com.rentCar.model.FuelType;
import com.rentCar.model.TransmissionType;
import com.rentCar.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.security.PermitAll;
import java.util.List;

@Controller
@RequestMapping(value = "codebook")
@CrossOrigin("http://localhost:4200")
public class CodebookController {

    @Autowired
    private CarBrandService carBrandService;
    @Autowired
    private CarModelService carModelService;
    @Autowired
    private CarClassService carClassService;
    @Autowired
    private FuelTypeService fuelTypeService;
    @Autowired
    private TransmissionTypeService transTypeService;


    @GetMapping(value="/getCodeBookInfo", produces="application/json")
    @PermitAll
    public ResponseEntity<?> getCodeBookInfo()
    {
        try {
            List<String> carBrands = this.carBrandService.findAllStringList();
            List<String> carClasses = this.carClassService.findAllStringList();
            List<String> fuelTypes = this.fuelTypeService.findAllStringList();
            List<String> transmissionTypes = this.transTypeService.findAllStringList();
            CodeBookDTO codeBook = new  CodeBookDTO(carBrands,carClasses, fuelTypes,transmissionTypes);

            return new ResponseEntity(codeBook, HttpStatus.OK);

        } catch (NullPointerException e) {
            return ResponseEntity.notFound().build();
        }
    }


    @GetMapping(value = "/getCodeBookInfoModel", produces = "application/json")
    //@PreAuthorize("hasRole('ADMIN')"
    public ResponseEntity<?> getCodeBookInfoModel() {
        try {
            List<CarBrand> carBrands = this.carBrandService.findAll();
            List<CarClass> carClasses = this.carClassService.findAll();
            List<FuelType> fuelTypes = this.fuelTypeService.findAll();
            List<TransmissionType> transmissionTypes = this.transTypeService.findAll();
            CodeBookModelDTO codeBook = new CodeBookModelDTO(carBrands, carClasses, fuelTypes, transmissionTypes);

            return new ResponseEntity(codeBook, HttpStatus.OK);

        } catch (NullPointerException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
