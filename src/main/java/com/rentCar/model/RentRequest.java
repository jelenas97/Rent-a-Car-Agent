package com.rentCar.model;

import com.rentCar.enumerations.RentRequestStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class RentRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private RentRequestStatus rentRequestStatus;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Client client;

    @ManyToMany(mappedBy = "rentRequests", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Advertisement> advertisements;
}
