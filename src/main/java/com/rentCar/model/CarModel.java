package com.rentCar.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class CarModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column
    private Boolean active = true;

    @OneToMany(mappedBy = "carModel", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    public Set<CarBrand> carBrand;

    public CarModel(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
