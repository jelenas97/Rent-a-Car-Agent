package com.rentCar.service.impl;

import com.rentCar.dto.StatisticDTO;
import com.rentCar.model.Advertisement;
import com.rentCar.model.RentReport;
import com.rentCar.repository.RentReportRepository;
import com.rentCar.service.AdvertisementService;
import com.rentCar.service.RentReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
public class RentReportServiceImpl implements RentReportService {

    @Autowired
    private RentReportRepository rentReportRepository;

    @Autowired
    private AdvertisementService advertisementService;

    @Override
    public List<RentReport> findAll() {
        return rentReportRepository.findAll();
    }

    @Override
    public void save(RentReport rentReport) {
        rentReportRepository.save(rentReport);
    }

    @Override
    public List<StatisticDTO> getMostKmByUserId(Long id) {
        List<Advertisement> ads = this.advertisementService.findAll(id);
        List<StatisticDTO> statistics = new ArrayList<StatisticDTO>();
        for (Advertisement ad : ads) {
            Long km = 0l;
            List<RentReport> reports = this.rentReportRepository.findAllCount(ad.getId());
            for (RentReport rentReport : reports) {
                km += rentReport.getKilometers();
            }
            StatisticDTO statisticDTO = new StatisticDTO();
            statisticDTO.setKm(km);
            statisticDTO.setCarName(ad.getCar().getName());
            statistics.add(statisticDTO);
        }
        statistics.sort(Comparator.comparing(StatisticDTO::getKm).reversed());
        return statistics;
    }
}
