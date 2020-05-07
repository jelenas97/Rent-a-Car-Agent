package com.rentCar.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class RequestsHolder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private Boolean bundle;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    public Set<RentRequest> rentRequests;
}
