package com.rentCar.dto;

import com.rentCar.model.Agent;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AgentDTO {

    private Long id;
    private String email;
    private String status;
    private String username;
    private String password;
    private String confirmPassword;
    private String firstName;
    private String lastName;
    private String address;
    private List<String> roles;
    private String businessRegistrationNumber;

    public AgentDTO(Agent agent){
        this.id=agent.getId();
        this.email = agent.getEmail();
        this.firstName=agent.getFirstName();
        this.lastName=agent.getLastName();
        this.address=agent.getAddress();
        this.businessRegistrationNumber=agent.getBusinessRegistrationNumber();
    }
}
