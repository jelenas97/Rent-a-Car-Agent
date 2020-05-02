package com.rentCar.controller;

import com.rentCar.dto.UserDTO;
import com.rentCar.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(value = "users")
@CrossOrigin("http://localhost:4200")
public class UserController {
    @Autowired
    private UserServiceImpl userService;

    @GetMapping(produces="application/json")
    //@PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> getUsers(){

        try {
            List<UserDTO> users = this.userService.findAllUsers();

            return new ResponseEntity(users, HttpStatus.OK);

        }catch(NullPointerException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Error while loading users");
        }
    }
    @PutMapping(value="/changeStatus", produces = "application/json", consumes = "application/json")
    //@PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity changeStatus(@RequestBody UserDTO user) {

        try {
           this.userService.changeStatus(user);
           return new ResponseEntity(HttpStatus.OK);

        } catch (NullPointerException e) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }
}