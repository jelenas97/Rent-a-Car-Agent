package com.rentCar.dto;

import com.rentCar.enumerations.RentRequestStatus;
import com.rentCar.model.Advertisement;
import com.rentCar.model.RentRequest;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Set;
@Getter
@Setter
@NoArgsConstructor
public class RentRequestDTO {


    private Long id;
    private LocalDate startDate;
    private LocalDate endDate;
    private String rentRequestStatus;
    private String sender_email;
    private Set<AdvertisementDTO> advertisements;

    public RentRequestDTO(RentRequest rentRequest){
        this.id = rentRequest.getId();
        this.startDate = rentRequest.getStartDate();
        this.endDate = rentRequest.getEndDate();
        this.sender_email = rentRequest.getSender().getEmail();
        this.rentRequestStatus = rentRequest.getRentRequestStatus().toString();
        for(Advertisement ad : rentRequest.getAdvertisements()){
            this.advertisements.add(new AdvertisementDTO(ad));
        }
    }
}
