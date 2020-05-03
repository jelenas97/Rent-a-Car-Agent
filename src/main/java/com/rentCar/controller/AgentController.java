package com.rentCar.controller;

import com.rentCar.model.Agent;
import com.rentCar.service.AgentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@CrossOrigin("http://localhost:4200")
@RequestMapping("/agent")
@RequiredArgsConstructor
public class AgentController {

    private final AgentService agentService;

    @RequestMapping("/save")
    public Agent save(@RequestBody Agent agent){
        return agentService.save(agent);
    }

}
