package com.rentCar.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CodeBookDTO {
    private List<CarBrandDto> carBrands;
    private List<CarClassDto> carClasses;
    private List<FuelTypeDto> fuelTypes;
    private List<TransmissionTypeDto> transmissionTypes;
}
