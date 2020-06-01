package com.rentCar.dto;

import com.rentCar.model.PriceList;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PricelistDTO {

    private long id;
    private double pricePerDay;
    private double pricePerKm;
    private double cdw;
    private long creatorId;

    public PricelistDTO(double pricePerDay, double pricePerKm, double cdw, long creatorId) {
        this.pricePerDay = pricePerDay;
        this.pricePerKm = pricePerKm;
        this.cdw = cdw;
        this.creatorId = creatorId;
    }

    public PricelistDTO(PriceList priceList){
        this.creatorId=priceList.getCreator().getId();
        this.pricePerDay=priceList.getPricePerDay();
        this.pricePerKm=priceList.getPricePerKm();
        this.cdw=priceList.getCdw();
        this.id=priceList.getId();
    }
}
