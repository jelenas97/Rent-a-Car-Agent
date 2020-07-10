package com.rentCar.controller;

import com.rentCar.dto.AdvertisementDTO;
import com.rentCar.dto.TermDTO;
import com.rentCar.model.Term;
import com.rentCar.service.TermService;
import com.rentCar.soap.ReportClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "terms")
@CrossOrigin("http://localhost:4200")
public class TermController {


    @Autowired
    private TermService termService;

    @Autowired
    private ReportClient reportClient;

    @PostMapping(produces = "application/json")
    // @PreAuthorize("hasRole('')")
    public ResponseEntity<?> newTerm() {

        try {
            return new ResponseEntity(null, HttpStatus.OK);
        } catch (NullPointerException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Error during searching");
        }
    }


    @GetMapping(consumes = "application/json", value = "/withReports")
    public List<TermDTO> getAllWithReports() {

        List<Term> terms = termService.getAllWithReports();
        List<TermDTO> termDTOS = new ArrayList<>();

        for (Term t : terms) {
            if (t.getReportWritten()) {
                TermDTO termDTO = new TermDTO();
                termDTO.setId(t.getId());
                termDTO.setStartDate(t.getStartDate());
                termDTO.setEndDate(t.getEndDate());

                AdvertisementDTO advertisementDTO = new AdvertisementDTO();
                advertisementDTO.setId(t.getAdvertisement().getId());
                advertisementDTO.setCarBrand(t.getAdvertisement().getCar().getCarBrand().getName());
                advertisementDTO.setName(t.getAdvertisement().getCar().getName());
                advertisementDTO.setStartDate(t.getAdvertisement().getStartDate());
                advertisementDTO.setEndDate(t.getAdvertisement().getEndDate());

                termDTOS.add(termDTO);
            }
        }

        return termDTOS;
    }

    @GetMapping(value = "/agent/{id}", produces = "application/json")
    @PreAuthorize("hasAuthority('ROLE_AGENT')")
    public List<TermDTO> getAllRentedFromCurrentAgent(@PathVariable Long id) {
        List<TermDTO> rented = this.reportClient.getTerms(id);
        return rented;
    }

}
