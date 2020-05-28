package com.rentCar.service;

import com.rentCar.model.Agent;

public interface AgentService {
    Agent save(Agent agent);
    Agent findById(long id);
}
