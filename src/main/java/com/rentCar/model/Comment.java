package com.rentCar.model;

import com.rentCar.enumerations.ApproveStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private LocalDate date;

    @Column
    private String content;

    @Enumerated(EnumType.STRING)
    private ApproveStatus status = ApproveStatus.UNPROCESSED;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    public Advertisement advertisement;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    public User user;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    RentRequest rentRequest;
}
