package com.rentCar.service;

import com.rentCar.dto.StatisticDTO;
import com.rentCar.model.RentReport;

import java.util.List;

public interface RentReportService {

    List<RentReport> findAll();

    void save(RentReport rentReport);

    List<StatisticDTO> getMostKmByUserId(Long id);
}
