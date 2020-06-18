package com.rentCar.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    public RentRequest rentRequest;

    @Column
    private String content;
    @Column
    private LocalDateTime date;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    public User sender;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    public User recepient;
    @Column
    private Boolean seen;
}
