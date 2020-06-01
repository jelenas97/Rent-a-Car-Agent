package com.rentCar.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class CarBrand {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column
    private Boolean active = true;

    @JsonIgnore
    @OneToMany(mappedBy = "carBrand", fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    public Set<Car> car;

    @OneToMany(mappedBy = "carBrand", fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    public Set<CarModel> carModels;

    public CarBrand(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
