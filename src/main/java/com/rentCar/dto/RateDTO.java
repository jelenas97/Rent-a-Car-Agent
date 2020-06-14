package com.rentCar.dto;

import com.rentCar.model.Comment;
import com.rentCar.model.Rate;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class RateDTO {

    private long id;
    private long value;
    private long client_id;
    private String client;
    private long advertisement_id;
    private long rent_request_id;
    private float average_rate;
    private long voted;

    public RateDTO(Rate rate, float average, long voted){
        this.id = rate.getId();
        this.value = rate.getValue();
        this.client_id=rate.getClient().getId();
        this.advertisement_id = rate.getAdvertisement().getId();
        this.client= rate.getClient().getFirstName()+" "+ rate.getClient().getLastName();
        this.rent_request_id= rate.getRentRequest().getId();
        this.average_rate= average;
        this.voted= voted;
    }
}
