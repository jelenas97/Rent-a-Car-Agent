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
@DiscriminatorValue(value="AGENT")
public class Agent extends User{

    @OneToMany(mappedBy = "agent", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    public Set<PriceList> priceList;

    @Column
    private String businessRegistrationNumber;
}
