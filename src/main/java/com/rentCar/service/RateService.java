package com.rentCar.service;

import com.rentCar.dto.RateDTO;

import java.util.List;

public interface RateService {

    long rateAdvertisement(RateDTO dto);
    List<RateDTO> findAverageAdvRate(long id);
}
