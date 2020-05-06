package com.rentCar.model;

import com.rentCar.dto.RentRequestDTO;
import com.rentCar.enumerations.RentRequestStatus;
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
public class RentRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="startDate",nullable = false)
    private LocalDate startDate;

    @Column(name="endDate", nullable = false)
    private LocalDate endDate;

    @Enumerated(EnumType.STRING)
    private RentRequestStatus rentRequestStatus;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private User sender;

    @ManyToMany(mappedBy = "rentRequests", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Advertisement> advertisements;


    public RentRequest(RentRequestDTO dto,User user, Set<Advertisement> advertisements){
        this.id = dto.getId();
        this.startDate = dto.getStartDate();
        this.endDate = dto.getEndDate();
        this.rentRequestStatus = RentRequestStatus.valueOf(dto.getRentRequestStatus());
        this.sender = user;
        this.advertisements = advertisements;
    }
}
