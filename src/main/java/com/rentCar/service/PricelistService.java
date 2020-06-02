package com.rentCar.service;

import com.rentCar.dto.PricelistDTO;
import com.rentCar.model.PriceList;

import java.util.List;

public interface PricelistService {

    PriceList createNewPricelist(PricelistDTO dto);
    List<PricelistDTO> getCreatorsPricelists(Long id);
}
