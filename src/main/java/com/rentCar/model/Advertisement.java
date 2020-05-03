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
public class Advertisement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private Integer kilometresLimit;

    @Column
    private Integer discount;
    @Column
    private String place;

    @Column
    private Boolean cdw;

    @Column(name="startDate",nullable = false)
    private LocalDate startDate;

    @Column(name="endDate", nullable = false)
    private LocalDate endDate;

    @OneToMany(mappedBy = "advertisement", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    public Set<Comment> comment;
    @OneToMany(mappedBy = "advertisement", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    public Set<Rate> rate;

    @OneToOne
    public Car car;
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    public User owner;
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    public PriceList priceList;
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    public Set<RentRequest> rentRequests;



}
