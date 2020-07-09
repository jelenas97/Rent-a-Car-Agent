package com.rentCar.controller;

import com.rentCar.dto.AgentDTO;
import com.rentCar.model.Agent;
import com.rentCar.model.Authority;
import com.rentCar.service.AgentService;
import com.rentCar.service.AuthorityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin("http://localhost:4200")
@RequestMapping(value = "agent")
public class AgentController {

    @Autowired
    private AgentService agentService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthorityService authorityService;

    @PostMapping
    public Agent save(@RequestBody Agent agent) {
        Authority authority = authorityService.findByName("ROLE_AGENT");
        List<Authority> authorities = new ArrayList<>();
        authorities.add(authority);
        agent.setAuthorities(authorities);
        agent.setPassword(passwordEncoder.encode(agent.getPassword()));
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
