package com.rentCar.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Advertisement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private Long microId;

    @Column
    private Integer kilometresLimit;

    @Column
    private Integer discount;
    @Column
    private String place;


    @Column(name="startDate",nullable = false)
    private LocalDate startDate;

    @Column(name="endDate", nullable = false)
    private LocalDate endDate;

    @OneToMany(mappedBy = "advertisement", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    public Set<Comment> comment;
    @OneToMany(mappedBy = "advertisement", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    public Set<Rate> rate;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    public Car car;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    public User owner;
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    public PriceList priceList;
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    public Set<RentRequest> rentRequests;

    @OneToMany(mappedBy = "advertisement", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Term> terms;



}
