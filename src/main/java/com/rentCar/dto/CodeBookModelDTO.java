package com.rentCar.dto;

import com.rentCar.model.CarBrand;
import com.rentCar.model.CarClass;
import com.rentCar.model.FuelType;
import com.rentCar.model.TransmissionType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class CodeBookModelDTO {
    private List<CarBrand> carBrands;
    private List<CarClass> carClasses;
    private List<FuelType> fuelTypes;
    private List<TransmissionType> transmissionTypes;

    public CodeBookModelDTO(List<CarBrand> carBrands, List<CarClass> carClasses, List<FuelType> fuelTypes, List<TransmissionType> transmissionTypes) {
        this.carBrands = carBrands;
        this.carClasses = carClasses;
        this.fuelTypes = fuelTypes;
        this.transmissionTypes = transmissionTypes;
    }
}
