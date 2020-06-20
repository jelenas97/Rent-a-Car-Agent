package com.rentCar.controller;

import com.rentCar.dto.PricelistDTO;
import com.rentCar.model.PriceList;
import com.rentCar.service.PricelistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(value = "priceList")
@CrossOrigin("http://localhost:4200")

public class PricelistController {

    @Autowired
    private PricelistService pricelistService;

    @PostMapping(produces = "application/json", consumes = "application/json")
    @PreAuthorize("hasAuthority('ROLE_AGENT') or hasAuthority('ROLE_CLIENT')")
    public ResponseEntity createPricelist(@RequestBody PricelistDTO dto) {

        PriceList pricelist =this.pricelistService.createNewPricelist(dto);

        return new ResponseEntity(pricelist.getId(), HttpStatus.CREATED);
    }

    @GetMapping(value = "/creator/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAuthority('ROLE_AGENT') or hasAuthority('ROLE_CLIENT')")
    public ResponseEntity<List<PricelistDTO>> getOwnersPricelists(@PathVariable String id) {

        try {
            List<PricelistDTO> list = new ArrayList<>();
            list =pricelistService.getCreatorsPricelists(Long.parseLong(id));
            return new ResponseEntity<List<PricelistDTO>>(list, HttpStatus.OK);
        } catch (Exception e) {
            System.out.println(e);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
