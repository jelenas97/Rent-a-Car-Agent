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
public class CarBrand {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @OneToMany(mappedBy = "carBrand", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    public Set<Car> car;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    public CarModel carModel;
}
