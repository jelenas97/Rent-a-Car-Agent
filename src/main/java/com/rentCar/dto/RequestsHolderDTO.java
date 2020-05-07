package com.rentCar.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class RequestsHolderDTO {
    private Long id;
    private Boolean bundle;
    private Set<RentRequestDTO> rentRequests;

    @Override
    public String toString() {
        return "RequestsHolderDTO{" +
                "id=" + id +
                ", bundle=" + bundle +
                ", rentRequests=" + rentRequests +
                '}';
    }
}
