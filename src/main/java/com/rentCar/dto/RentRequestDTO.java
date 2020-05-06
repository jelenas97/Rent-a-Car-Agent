package com.rentCar.dto;

import com.rentCar.enumerations.RentRequestStatus;
import com.rentCar.model.Advertisement;
import com.rentCar.model.RentRequest;

import java.time.LocalDate;
import java.util.Set;

public class RentRequestDTO {


    private Long id;
    private LocalDate startDate;
    private LocalDate endDate;
    private String rentRequestStatus;
    private String sender_id;
    private Set<AdvertisementDTO> advertisements;
    
    public RentRequestDTO(RentRequest rentRequest){
        this.id = rentRequest.getId();
        this.startDate = rentRequest.getStartDate();
        this.endDate = rentRequest.getEndDate();
        this.rentRequestStatus = rentRequest.getRentRequestStatus().toString();
        for(Advertisement ad : rentRequest.getAdvertisements()){
            this.advertisements.add(new AdvertisementDTO(ad));
        }
    }
}
