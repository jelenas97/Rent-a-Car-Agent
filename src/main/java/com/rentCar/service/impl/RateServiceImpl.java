package com.rentCar.service.impl;

import com.rentCar.dto.RateDTO;
import com.rentCar.model.Advertisement;
import com.rentCar.model.Client;
import com.rentCar.model.Rate;
import com.rentCar.model.RentRequest;
import com.rentCar.repository.AdvertisementRepository;
import com.rentCar.repository.ClientRepository;
import com.rentCar.repository.RateRepository;
import com.rentCar.repository.RentRequestRepository;
import com.rentCar.service.RateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
