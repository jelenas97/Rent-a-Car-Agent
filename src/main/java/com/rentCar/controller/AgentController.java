package com.rentCar.controller;

import com.rentCar.dto.AdvertisementDTO;
import com.rentCar.dto.AgentDTO;
import com.rentCar.dto.TermDTO;
import com.rentCar.model.Agent;
import com.rentCar.model.Term;
import com.rentCar.service.AgentService;
import com.rentCar.service.TermService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin("http://localhost:4200")
@RequestMapping(value = "agent")
public class AgentController {

    @Autowired
    private TermService termService;

    @Autowired
    private AgentService agentService;

    @RequestMapping("/save")
    public Agent save(@RequestBody Agent agent) {
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
    public List<TermDTO> getAllRentedFromCurrentAgent(@PathVariable Long id) {
        List<Term> rented = this.termService.getAllRentedFromCurrentAgent(id);
        List<TermDTO> termDTOS = new ArrayList<>();

        for (Term t : rented) {
            TermDTO termDTO = new TermDTO();
            termDTO.setId(t.getId());
            termDTO.setStartDate(t.getStartDate());
            termDTO.setEndDate(t.getEndDate());

            AdvertisementDTO advertisementDTO = new AdvertisementDTO();
            advertisementDTO.setId(t.getAdvertisement().getId());
            advertisementDTO.setName(t.getAdvertisement().getCar().getName());
            advertisementDTO.setCarBrand(t.getAdvertisement().getCar().getCarBrand().getName());
            advertisementDTO.setModel(t.getAdvertisement().getCar().getCarModel().getName());

            termDTO.setAdvertisement(advertisementDTO);

            termDTOS.add(termDTO);

        }

        System.out.println(termDTOS.size());
        return termDTOS;
    }
}
