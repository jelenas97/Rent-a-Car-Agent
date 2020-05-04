package com.rentCar.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@Getter
@Setter
@NoArgsConstructor
@DiscriminatorValue("COMPANY")
public class Company extends User{

    @Column
    private String name;

    @Column
    private Long PIB;

    @Column
    private String address;

    @Column
    private String city;
}
