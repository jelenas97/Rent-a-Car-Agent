package com.rentCar.controller;

import com.rentCar.RentCar.wsdl.PostAdResponse;
import com.rentCar.dto.AdvertisementDTO;
import com.rentCar.dto.SearchDTO;
import com.rentCar.model.Advertisement;
import com.rentCar.model.User;
import com.rentCar.service.AdvertisementService;
import com.rentCar.service.CarService;
import com.rentCar.service.UserService;
import com.rentCar.soap.AdClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.PermitAll;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(value = "advertisement")
@CrossOrigin("http://localhost:4200")
public class AdvertisementController {

    @Autowired
    private AdvertisementService advertisementService;

    @Autowired
    private CarService carService;

    @Autowired
    private UserService userService;

    @Autowired
    private AdClient adClient;

    //alternativa je 10+ parametar u arg
    @PostMapping(value = "/searchAds", produces = "application/json")
    @PermitAll
    public ResponseEntity<?> searchAdvertisements(@RequestBody SearchDTO searchDto) {

        try {
            System.out.println("SearchDto: : " + searchDto.toString());
            List<Advertisement> ads = this.advertisementService.search(searchDto);
            List<AdvertisementDTO> adsDto = new ArrayList<>();
            for(Advertisement ad : ads){
                adsDto.add(new AdvertisementDTO(ad));
            }
            return new ResponseEntity(adsDto, HttpStatus.OK);

        }catch(NullPointerException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Error during searching");
        }
    }


    @GetMapping(produces = "application/json")
    @PermitAll
    public ResponseEntity<?> getAllAdvertisements() {
        try {
            List<Advertisement> ads = this.advertisementService.findAll();
            System.out.println(ads);
            List<AdvertisementDTO> adsDto = new ArrayList<>();
            for (Advertisement ad : ads) {
                adsDto.add(new AdvertisementDTO(ad));
            }
            return new ResponseEntity(adsDto, HttpStatus.OK);

        } catch (NullPointerException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping(value = "/{id}", produces = "application/json")
    @PreAuthorize("hasRole('AGENT')")
    public ResponseEntity<?> getAgentAds(@PathVariable Long id) {
        try {
            List<Advertisement> ads = this.advertisementService.findAll(id);
            List<AdvertisementDTO> adsDto = new ArrayList<>();
            for (Advertisement ad : ads) {
                adsDto.add(new AdvertisementDTO(ad));
            }
            System.out.println(adsDto);
            return new ResponseEntity(adsDto, HttpStatus.OK);

        } catch (NullPointerException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping(consumes = "application/json")
    public ResponseEntity<?> addAdvertisement(@RequestBody Advertisement ad) {
        try {
            User user = this.userService.findById(3l);
            if (user.getAuthorities().get(0).getName().equals("ROLE_CLIENT")) {
                int numberOfAds = this.advertisementService.findAllCount(ad.getOwner().getId());
                if (numberOfAds >= 3) {
                    return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).body("You can not create more than 3 advertisement");
                }
            }
            PostAdResponse adPosted = adClient.postAd(ad);

            if (adPosted.getIdAd() == 0) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Soap error");
            }

            ad.setMicroId(adPosted.getIdAd());
            ad.getCar().setMicroId(adPosted.getIdCar());
            ad.setOwner(user);

            this.carService.add(ad.getCar());
            this.advertisementService.add(ad);

            return ResponseEntity.ok().build();
        } catch (NullPointerException e) {
            return ResponseEntity.notFound().build();
        }
    }


}
