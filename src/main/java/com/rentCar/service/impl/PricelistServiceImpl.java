package com.rentCar.service.impl;

import com.rentCar.dto.PricelistDTO;
import com.rentCar.model.PriceList;
import com.rentCar.model.User;
import com.rentCar.repository.PriceListRepository;
import com.rentCar.repository.UserRepository;
import com.rentCar.service.PricelistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PricelistServiceImpl implements PricelistService {

    @Autowired
    private PriceListRepository priceListRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public PriceList createNewPricelist(PricelistDTO dto) {

        User user = new User();

        PriceList priceList = new PriceList();
        priceList.setCdw(dto.getCdw());
        priceList.setPricePerDay(dto.getPricePerDay());
        priceList.setPricePerKm(dto.getPricePerKm());
        user= userRepository.getOne(dto.getCreatorId());
        priceList.setCreator(user);
        this.priceListRepository.save(priceList);

        return priceList;
    }

    @Override
    public List<PricelistDTO> getCreatorsPricelists(Long id) {

        List<PricelistDTO> dtoList = new ArrayList<>();

        List<PriceList> list = priceListRepository.findByCreatorId(id);
        for (PriceList p : list) {
            dtoList.add(new PricelistDTO(p));
        }
        return dtoList;
    }
}
