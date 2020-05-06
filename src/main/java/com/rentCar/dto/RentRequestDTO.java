package com.rentCar.dto;

import com.rentCar.model.Advertisement;
import com.rentCar.model.RentRequest;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class RentRequestDTO {

    private Long id;
    private String startDateTime;
    private String endDateTime;
    private String rentRequestStatus;
    private String cars;
    private String advertisementId;


    public RentRequestDTO(RentRequest rr){

        this.id=rr.getId();
        String[] dateTime=  rr.getStartDateTime().toString().split("T");
        this.startDateTime=dateTime[0]+"\r\n"+dateTime[1];
        String[] dateTime1=  rr.getEndDateTime().toString().split("T");
        this.endDateTime=dateTime1[0]+" "+dateTime1[1];
        this.rentRequestStatus=rr.getRentRequestStatus().toString();

        this.cars ="";
        this.advertisementId="";
        for(Advertisement a : rr.getAdvertisements()){
            this.cars+= a.getCar().getCarBrand().getName()+ " / " + a.getCar().getCarClass().toString() + " / " + a.getCar().getCarModel().getName() + "\r\n" ;
            this.advertisementId=a.getId().toString();
        }

    }

}
