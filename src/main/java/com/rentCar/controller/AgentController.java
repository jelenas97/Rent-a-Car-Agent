package com.rentCar.controller;

import com.rentCar.dto.AgentDTO;
import com.rentCar.model.Agent;
import com.rentCar.model.Term;
import com.rentCar.service.AgentService;
import com.rentCar.service.TermService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@CrossOrigin("http://localhost:4200")
@RequestMapping(value = "/agent")
@RequiredArgsConstructor
public class AgentController {

    private final TermService termService;
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

    @GetMapping(value = "/{id}/terms", produces = "application/json")
    @PreAuthorize("hasRole('AGENT')")
    public ResponseEntity<?> getAllRentedFromCurrentAgent(@PathVariable Long id) {
        try {
            List<Term> rented = this.termService.getAllRentedFromCurrentAgent(id);
            return new ResponseEntity(rented, HttpStatus.OK);

        } catch (NullPointerException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
