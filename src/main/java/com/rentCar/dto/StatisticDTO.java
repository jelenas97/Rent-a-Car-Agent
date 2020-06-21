package com.rentCar.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StatisticDTO {

    private String carName;
    private Long km;
    private Double rate;
    private Long comment;

}
