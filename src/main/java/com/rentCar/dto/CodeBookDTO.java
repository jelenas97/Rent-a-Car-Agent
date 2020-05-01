package com.rentCar.dto;

import com.rentCar.model.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.util.List;
@Getter
@Setter
@NoArgsConstructor
public class CodeBookDTO {
    private List<String> carBrands;
    private List<String> carClasses;
    private List<String> fuelTypes;
    private List<String> transmissionTypes;
    public CodeBookDTO(List<String> carBrands,List<String> carClasses,
                        List<String> fuelTypes,List<String> transmissionTypes){

        this.carBrands = carBrands;
        this.carClasses = carClasses;
        this.fuelTypes = fuelTypes;
        this.transmissionTypes = transmissionTypes;
    }
}
