package com.rentCar.controller;


import com.rentCar.dto.*;
import com.rentCar.model.CarBrand;
import com.rentCar.model.CarClass;
import com.rentCar.model.FuelType;
import com.rentCar.model.TransmissionType;
import com.rentCar.dto.CodeBookDTO;
import com.rentCar.dto.CodeBookModelDTO;
import com.rentCar.dto.PricelistDTO;
import com.rentCar.model.CarBrand;
import com.rentCar.model.CarClass;
import com.rentCar.model.FuelType;
import com.rentCar.model.TransmissionType;
import com.rentCar.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.stream.Collectors;

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
    @Autowired
    private PricelistService priceListService;


    @GetMapping(value = "/getCodeBookInfo", produces = "application/json")
    public ResponseEntity<?> getCodeBookInfo() {
        try {
            List<CarBrand> carBrands = this.carBrandService.findAllActive();
            List<CarClass> carClasses = this.carClassService.findAllActive();
            List<FuelType> fuelTypes = this.fuelTypeService.findAllActive();
            List<TransmissionType> transmissionTypes = this.transTypeService.findAllActive();

            List<CarClassDto> carClassDtos = carClasses.stream()
                    .map(carClass -> new CarClassDto(carClass.getId(), carClass.getName()))
                    .collect(Collectors.toList());

            List<CarBrandDto> carBrandDtos = carBrands.stream()
                    .map(carBrand -> new CarBrandDto(carBrand.getId(), carBrand.getName()))
                    .collect(Collectors.toList());

            List<FuelTypeDto> fuelTypeDtos = fuelTypes.stream()
                    .map(fuelType -> new FuelTypeDto(fuelType.getId(), fuelType.getName()))
                    .collect(Collectors.toList());

            List<TransmissionTypeDto> transmissionTypeDtos = transmissionTypes.stream()
                    .map(transmissionType -> new TransmissionTypeDto(transmissionType.getId(), transmissionType.getName()))
                    .collect(Collectors.toList());

            CodeBookDTO codeBook = new CodeBookDTO(carBrandDtos, carClassDtos, fuelTypeDtos, transmissionTypeDtos);

            return new ResponseEntity<>(codeBook, HttpStatus.OK);

        } catch (NullPointerException e) {
            return ResponseEntity.notFound().build();
        }
    }


    @GetMapping(value = "/getCodeBookInfoModel/{id}", produces = "application/json")
    //@PreAuthorize("hasRole('ADMIN')"
    public ResponseEntity<?> getCodeBookInfoModel(@PathVariable Long id) {
        try {
            List<CarBrand> carBrands = this.carBrandService.findAll();
            List<CarClass> carClasses = this.carClassService.findAll();
            List<FuelType> fuelTypes = this.fuelTypeService.findAll();
            List<TransmissionType> transmissionTypes = this.transTypeService.findAll();
            List<PricelistDTO> priceLists = this.priceListService.getCreatorsPricelists(id);
            CodeBookModelDTO codeBook = new CodeBookModelDTO(carBrands, carClasses, fuelTypes, transmissionTypes, priceLists);

            return new ResponseEntity(codeBook, HttpStatus.OK);

        } catch (NullPointerException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
