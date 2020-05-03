package com.rentCar.service.impl;

import com.rentCar.model.Agent;
import com.rentCar.repository.AgentRepository;
import com.rentCar.service.AgentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AgentServiceImpl implements AgentService {

    private final AgentRepository agentRepository;

    @Override
    public Agent save(Agent agent) {
        return agentRepository.save(agent);
    }
}
