package com.rentCar.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class SearchDTO<any> {

    private String place;
    private LocalDate startDate;
    private LocalDate endDate;
    private String brand;
    private String fuelType;
    private String carClass;
    private Integer limitMileage;
    private String transmissionType;
    private String carModel;
    private Boolean dmg;
    private Integer seats;
    private Integer mileage;
    private Double minPrice;
    private Double maxPrice;

}
