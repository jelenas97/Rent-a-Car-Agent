package com.rentCar.service;

import com.rentCar.dto.SearchDTO;
import com.rentCar.model.Advertisement;

import java.util.List;

public interface AdvertisementService {
    List<Advertisement> search(SearchDTO dto);
    Advertisement find(Long id);

    List<Advertisement> findAll();

    void add(Advertisement ad);

    List<Advertisement> findAll(Long agentID);

    int findAllCount(Long id);
}
