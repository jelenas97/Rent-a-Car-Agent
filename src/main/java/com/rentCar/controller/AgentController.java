package com.rentCar.controller;

import com.rentCar.dto.AgentDTO;
import com.rentCar.model.Agent;
import com.rentCar.model.User;
import com.rentCar.service.AgentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@CrossOrigin("http://localhost:4200")
@RequestMapping(value="/agent")
@RequiredArgsConstructor
public class AgentController {

    private final AgentService agentService;

    @RequestMapping("/save")
    public Agent save(@RequestBody Agent agent){
        return agentService.save(agent);
    }

    @GetMapping(value = "/{id}", produces = "application/json")
    @PreAuthorize("hasAuthority('ROLE_AGENT')")
    public ResponseEntity<?> getUserInfo(@PathVariable long id) {
        Agent a = this.agentService.findById(id);
        AgentDTO aDTO = new AgentDTO(a);
        return new ResponseEntity(aDTO, HttpStatus.OK);
    }
}
