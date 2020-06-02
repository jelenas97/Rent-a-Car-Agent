package com.rentCar.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class RateDTO {

    private long id;
    private long value;
    private long client_id;
    private long advertisement_id;
    private long rent_request_id;
}
