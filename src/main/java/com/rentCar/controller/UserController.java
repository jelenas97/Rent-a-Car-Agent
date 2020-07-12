package com.rentCar.controller;

import com.rentCar.dto.UserDTO;
import com.rentCar.enumerations.AccountStatus;
import com.rentCar.model.User;
import com.rentCar.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping(value = "/user")
@CrossOrigin("http://localhost:4200")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping(produces="application/json")
    public ResponseEntity<?> getUsers(){

        try {
            List<UserDTO> users = this.userService.findAllUsers();

            return new ResponseEntity<>(users, HttpStatus.OK);

        }catch(NullPointerException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Error while loading users");
        }
    }
    @PutMapping(value="/changeStatus", produces = "application/json", consumes = "application/json")
    public ResponseEntity<?> changeStatus(@RequestBody UserDTO user) {

        try {
            this.userService.changeStatus(user);
            return new ResponseEntity<>(HttpStatus.OK);

        } catch (NullPointerException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    @GetMapping("/whoami")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_CLIENT', 'ROLE_AGENT')")
    public User user(Principal user) {
        return this.userService.findByUsername(user.getName());
    }

    @PostMapping("/username")
    public AccountStatus userByUsername(@RequestBody String username) {
        return this.userService.findByUsername(username).getStatus();
    }
}
