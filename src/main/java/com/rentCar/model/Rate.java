package com.rentCar.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Rate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private Integer value;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    public Client client;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    public Advertisement advertisement;
}
