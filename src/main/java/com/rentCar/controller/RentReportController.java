package com.rentCar.controller;

import com.rentCar.RentCar.wsdl.PostReportResponse;
import com.rentCar.model.RentReport;
import com.rentCar.model.Term;
import com.rentCar.service.RentReportService;
import com.rentCar.service.TermService;
import com.rentCar.soap.ReportClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "report")
@CrossOrigin("http://localhost:4200")
public class RentReportController {

    @Autowired
    private TermService termService;

    @Autowired
    private ReportClient reportClient;

    @Autowired
    private RentReportService rentReportService;


    @PostMapping(consumes = "application/json")
    public ResponseEntity save(@RequestBody RentReport rentReport) {

        try {
            Term term = rentReport.getTerm();
            term.setReportWritten(true);
            term.setAdvertisement(rentReport.getAdvertisement());
            termService.save(term);
            rentReportService.save(rentReport);

            PostReportResponse response = reportClient.save(rentReport);

            return ResponseEntity.ok().build();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.notFound().build();
        }

    }

    @GetMapping(produces = "application/json")
    public List<RentReport> getAll() {
        return rentReportService.findAll();
    }

}
