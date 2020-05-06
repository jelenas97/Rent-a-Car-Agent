package com.rentCar.service.impl;

import com.rentCar.dto.SearchDTO;
import com.rentCar.model.Advertisement;
import com.rentCar.model.FuelType;
import com.rentCar.repository.AdvertisementRepository;
import com.rentCar.repository.FuelTypeRepository;
import com.rentCar.service.AdvertisementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Service
public class AdvertisementServiceImpl implements AdvertisementService {

    @Autowired
    private AdvertisementRepository advertisementRepository;

    @Autowired
    private FuelTypeRepository fuelTypeRepository;

    @Override
    public List<Advertisement> search(SearchDTO dto) {
        List<Advertisement> ads = this.advertisementRepository.findAdvertisements(dto.getPlace(),dto.getStartDate(),dto.getEndDate());
        System.out.println(dto);
        System.out.println(ads);
        FuelType fuelType = this.fuelTypeRepository.findByName(dto.getFuelType());

        List<Predicate<Advertisement> > predicates = new ArrayList<>();

        if(dto.getLimitMileage() != null){
            Predicate<Advertisement> byLimitMileage = ad -> ad.getKilometresLimit() >= dto.getLimitMileage();
            predicates.add(byLimitMileage);
        }
        if(dto.getDmg() != null){
            Predicate<Advertisement> byCwd = ad -> ad.getCdw() == dto.getDmg();
            predicates.add(byCwd);
        }
        if(dto.getBrand() != null){
            Predicate<Advertisement> byBrand = ad -> ad.getCar().getCarBrand().getName().equals(dto.getBrand());
            predicates.add(byBrand);
        }
        if(dto.getFuelType() != null){
            Predicate<Advertisement> byFuel = ad -> ad.getCar().getFuelType().contains(fuelType);
            predicates.add(byFuel);
        }
        if(dto.getCarClass() != null){
            Predicate<Advertisement> byClass = ad -> ad.getCar().getCarClass().getName().equals(dto.getCarClass());
            predicates.add(byClass);
        }
        if(dto.getTransmissionType() != null){
            Predicate<Advertisement> byTrans = ad -> ad.getCar().getTransmissionType().getName().equals(dto.getTransmissionType());
            predicates.add(byTrans);
        }
        if(dto.getSeats() != null){
            Predicate<Advertisement> byKidSeats = ad -> ad.getCar().getKidSeats().equals(dto.getSeats());
            predicates.add(byKidSeats);
        }
        if(dto.getMileage() != null){
            Predicate<Advertisement> byMileage = ad -> ad.getCar().getMileage().equals(dto.getMileage());
            predicates.add(byMileage);
        }
        if(dto.getCarModel() != null){
            Predicate<Advertisement> byModel = ad -> ad.getCar().getCarBrand().getName().equals(dto.getCarModel());
            predicates.add(byModel);
        }
        if(dto.getMaxPrice() != null){
            Predicate<Advertisement> byMaxPrice = ad -> ad.getPriceList().getPricePerDay() <= dto.getMaxPrice();
            predicates.add(byMaxPrice);
        }
        if(dto.getMinPrice() != null){
            Predicate<Advertisement> byMinPrice = ad -> ad.getPriceList().getPricePerDay() >= dto.getMinPrice();
            predicates.add(byMinPrice);
        }

        for(Predicate<Advertisement> predicate : predicates){
            List<Advertisement> result = ads.stream()
                    .filter(predicate)
                    .collect(Collectors.toList());
            ads = result;
        }

        System.out.println("Filtrated : " + ads);
        return ads;
    }

    @Override
    public Advertisement find(Long id) {

        return this.advertisementRepository.find(id);
    }
}
