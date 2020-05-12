package com.rentCar.controller;

import com.rentCar.dto.RequestsHolderDTO;
import com.rentCar.service.RequestsHolderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping(value = "requestHolder")
@CrossOrigin("http://localhost:4200")
public class RequestHolderController {

    @Autowired
    private RequestsHolderService requestsHolderService;

    @GetMapping(value = "/{id}", produces = "application/json")
    //@PreAuthorize("hasRole('AGENT') and hasRole('CLIENT')"
    public ResponseEntity<?> getRequestHolders(@PathVariable Long id) {
        try {
            List<RequestsHolderDTO> holders = this.requestsHolderService.getAllPending(id);
            return new ResponseEntity(holders, HttpStatus.OK);

        } catch (NullPointerException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
