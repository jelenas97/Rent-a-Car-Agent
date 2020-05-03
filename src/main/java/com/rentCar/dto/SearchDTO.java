package com.rentCar.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
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

    @Override
    public String toString() {
        return "SearchDTO{" +
                "place='" + place + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", brand='" + brand + '\'' +
                ", fuelType='" + fuelType + '\'' +
                ", carClass='" + carClass + '\'' +
                ", limitMileage=" + limitMileage +
                ", transmissionType='" + transmissionType + '\'' +
                ", carModel='" + carModel + '\'' +
                ", dmg=" + dmg +
                ", seats=" + seats +
                ", mileage=" + mileage +
                ", minPrice=" + minPrice +
                ", maxPrice=" + maxPrice +
                '}';
    }
}
