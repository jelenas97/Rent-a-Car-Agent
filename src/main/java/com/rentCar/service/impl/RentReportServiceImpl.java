package com.rentCar.service.impl;

import com.rentCar.model.RentReport;
import com.rentCar.repository.RentReportRepository;
import com.rentCar.service.RentReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RentReportServiceImpl implements RentReportService {

    @Autowired
    private RentReportRepository rentReportRepository;

    @Override
    public List<RentReport> findAll() {
        return rentReportRepository.findAll();
    }

    @Override
    public void save(RentReport rentReport) {
        rentReportRepository.save(rentReport);
    }
}
