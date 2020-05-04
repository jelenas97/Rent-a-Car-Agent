package com.rentCar.controller;

import com.rentCar.dto.AdvertisementDTO;
import com.rentCar.dto.SearchDTO;
import com.rentCar.model.Advertisement;
import com.rentCar.service.AdvertisementService;
import com.rentCar.service.impl.AdvertisementServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(value = "advertisement")
@CrossOrigin("http://localhost:4200")
public class AdvertisementController {

    @Autowired
    private AdvertisementService advertisementService;

    //alternativa je 10+ parametar u arg
    @PostMapping(value = "/searchAds", produces="application/json")
    // @PreAuthorize("hasRole('')")
    public ResponseEntity<?> searchAdvertisements(@RequestBody SearchDTO searchDto){

        try {
            //DTO ADVERTISEMENT NEMOJ ZABORAVITI!!!!

            System.out.println("SearchDto: : "  + searchDto.toString());
            List<Advertisement> ads = this.advertisementService.search(searchDto);
            List<AdvertisementDTO> adsDto = new ArrayList<>();
            for(Advertisement ad : ads){
                adsDto.add(new AdvertisementDTO(ad));
            }

            System.out.println("PRONADJENI OGLASI" + adsDto);
            return new ResponseEntity(adsDto, HttpStatus.OK);

        }catch(NullPointerException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Error during searching");
        }
    }

}
