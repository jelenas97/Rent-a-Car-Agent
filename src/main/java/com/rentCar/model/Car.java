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
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column
    private Double rate;

    @Column
    private Integer mileage;

    @Column
    private Integer kidSeats;

    @Column
    private Boolean availableTracking;

    @Column
    private String image;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    public Advertisement advertisement;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    public CarClass carClass;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    public CarBrand carBrand;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    public CarModel carModel;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    public TransmissionType transmissionType;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    public Set<FuelType> fuelType;


}
