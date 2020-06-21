package com.rentCar.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TermDTO {

    private Long id;
    private LocalDate startDate;
    private LocalDate endDate;
    private AdvertisementDTO advertisement;
}
