package com.rentCar.dto;

import com.rentCar.model.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.util.List;
@Entity
@Getter
@Setter
@NoArgsConstructor
public class CodeBookDTO {
    private List<CarBrand> carBrands;
    private List<CarClass> carClasses;
    private List<CarModel> carModels;
    private List<FuelType> fuelTypes;
    private List<TransmissionType> transmissionTypes;
    public CodeBookDTO(List<CarBrand> carBrands,List<CarClass> carClasses,List<CarModel> carModels,
                        List<FuelType> fuelTypes,List<TransmissionType> transmissionTypes){
        this.carBrands = carBrands;
        this.carClasses = carClasses;
        this.carModels = carModels;
        this.fuelTypes = fuelTypes;
        this.transmissionTypes = transmissionTypes;
    }
}
