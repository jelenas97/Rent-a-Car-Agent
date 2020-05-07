package com.rentCar.model;

import com.rentCar.dto.RentRequestDTO;
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

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private RequestsHolder requests;

    //new RentRequest with pending status
    public RentRequest(RentRequestDTO requestDTO, User sender, Advertisement advertisement, RequestsHolder holder) {
        this.startDateTime = requestDTO.getStartDateTime();
        this.endDateTime = requestDTO.getEndDateTime();
        this.rentRequestStatus = RentRequestStatus.PENDING;
        this.sender = sender;
        this.advertisement = advertisement;
        this.requests = holder;

    }



}
