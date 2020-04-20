package com.rentCar.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class FooController {

    @GetMapping("/foo")
    public String getMessage(){
        return "Hello from Spring! <3";
    }
}
