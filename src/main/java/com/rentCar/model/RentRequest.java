package com.rentCar.model;

import com.rentCar.enumerations.RentRequestStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class RentRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "startDateTime", nullable = false)
    private LocalDateTime startDateTime;

    @Column(name = "endDateTime", nullable = false)
    private LocalDateTime endDateTime;

    @Enumerated(EnumType.STRING)
    private RentRequestStatus rentRequestStatus;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private User sender;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Advertisement advertisement;

//    @ManyToMany(mappedBy = "rentRequests", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
//    private Set<RequestsHolder> requests;



}
