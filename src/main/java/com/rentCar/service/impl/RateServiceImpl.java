package com.rentCar.service.impl;

import com.rentCar.dto.CommentDTO;
import com.rentCar.dto.RateDTO;
import com.rentCar.model.*;
import com.rentCar.repository.AdvertisementRepository;
import com.rentCar.repository.ClientRepository;
import com.rentCar.repository.RateRepository;
import com.rentCar.repository.RentRequestRepository;
import com.rentCar.service.RateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RateServiceImpl implements RateService {

    @Autowired
    RateRepository rateRepository;
    @Autowired
    AdvertisementRepository advertisementRepository;
    @Autowired
    ClientRepository clientRepository;
    @Autowired
    RentRequestRepository rentRequestRepository;

    @Override
    public long rateAdvertisement(RateDTO dto) {

            Rate rate = new Rate();
            rate.setValue(dto.getValue());
            Advertisement adv = this.advertisementRepository.find(dto.getAdvertisement_id());
            rate.setAdvertisement(adv);
            Client client = this.clientRepository.findById(dto.getClient_id());
            rate.setClient(client);
            RentRequest rr= this.rentRequestRepository.find(dto.getRent_request_id());
            rate.setRentRequest(rr);
            Rate r = this.rateRepository.save(rate);

            return r.getId();
    }

    @Override
    public List<RateDTO> findAverageAdvRate(long id){

        List<Rate> rates = this.rateRepository.findByAdvertisementCarId(id);
        List<RateDTO> rateDTOS = new ArrayList<>();
        long i=0;
        long sum=0;
        float average=0;
        for(Rate rate : rates){
            i++;
            sum+=rate.getValue();
        }
        average= (float)sum/(float)i;
        for(Rate rate: rates){
            rateDTOS.add(new RateDTO(rate, average, i));
        }

        return rateDTOS;
    }

}
