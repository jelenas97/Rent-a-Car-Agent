package com.rentCar.controller;

import com.rentCar.model.RentReport;
import com.rentCar.model.Term;
import com.rentCar.service.RentReportService;
import com.rentCar.service.TermService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "report")
@CrossOrigin("http://localhost:4200")
public class RentReportController {

    @Autowired
    private RentReportService rentReportService;

    @Autowired
    private TermService termService;

    @PostMapping(consumes = "application/json")
    public void save(@RequestBody RentReport rentReport) {
        Term term = rentReport.getTerm();
        term.setReportWritten(true);
        term.setAdvertisement(rentReport.getAdvertisement());
        termService.save(term);
        rentReportService.save(rentReport);
    }

    @GetMapping(produces = "application/json")
    public List<RentReport> getAll() {
        return rentReportService.findAll();
    }

}
