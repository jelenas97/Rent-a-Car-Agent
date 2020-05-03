package com.rentCar.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.Period;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Term {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @Column(name="startDate",nullable = false)
    private LocalDate startDate;
    @Column(name="endDate", nullable = false)
    private LocalDate endDate;
    @Column(name="period", nullable = false)
    private Period period;
    @ManyToOne
    @JoinColumn(name="car_id", nullable=false)
    private Car car;
}
