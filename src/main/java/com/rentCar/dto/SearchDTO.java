package com.rentCar.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;
@Getter
@Setter
@NoArgsConstructor
public class SearchDTO<any> {

    private String place;
    private Date startDate;
    private Date endDate;
    private String brand;
    private String fuelType;
    private String carClass;
    private String limitMileage;
    private String transmissionType;
    private String carModel;
    private String dmg;
    private String seats;
    private String mileage;
    private String minPrice;
    private String maxPrice;

}
