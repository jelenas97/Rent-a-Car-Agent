package com.rentCar.dto;

import com.rentCar.model.RentRequest;
import com.rentCar.model.RequestsHolder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class RequestsHolderDTO {
    private Long id;
    private Boolean bundle;
    private Set<RentRequestDTO> rentRequests;

    public RequestsHolderDTO(RequestsHolder requestsHolder) {
        this.id = requestsHolder.getId();
        this.bundle = requestsHolder.getBundle();
        this.rentRequests = new HashSet<>();
        for (RentRequest req : requestsHolder.getRentRequests()) {
            this.rentRequests.add(new RentRequestDTO(req));
        }
    }

    @Override
    public String toString() {
        return "RequestsHolderDTO{" +
                "id=" + id +
                ", bundle=" + bundle +
                ", rentRequests=" + rentRequests +
                '}';
    }
}
