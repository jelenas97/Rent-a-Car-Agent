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
public class PriceList {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column()
    private Double pricePerDay;

    @Column
    private Double pricePerKm;

    @OneToMany(mappedBy = "priceList", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    public Set<Advertisement> advertisement;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    public Agent agent;
}
