package com.rentCar.controller;

import com.rentCar.dto.UserDTO;
import com.rentCar.enumerations.AccountStatus;
import com.rentCar.model.Authority;
import com.rentCar.model.RegisterRequest;
import com.rentCar.model.User;
import com.rentCar.service.AuthorityService;
import com.rentCar.service.RegisterRequestService;
import com.rentCar.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "registerRequest")
@CrossOrigin("http://localhost:4200")
public class RegisterRequestController {

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthorityService authorityService;

    @Autowired
    private RegisterRequestService registerRequestService;

    @PostMapping
    public void registerNewUser(@RequestBody UserDTO userDTO) {

        RegisterRequest registerRequest = new RegisterRequest();

        registerRequest.setUsername(userDTO.getUsername());
        registerRequest.setEmail(userDTO.getEmail());
        registerRequest.setFirstName(userDTO.getFirstName());
        registerRequest.setLastName(userDTO.getLastName());
        registerRequest.setPassword(userDTO.getPassword());

        registerRequestService.save(registerRequest);

    }

    @GetMapping
    public List<RegisterRequest> getAll() {
        return registerRequestService.getAll();
    }

    @PostMapping("/approve")
    public void approve(@RequestBody RegisterRequest registerRequest) {

        Authority authority = authorityService.findByName("ROLE_CLIENT");
        List<Authority> authorities = new ArrayList<>();
        authorities.add(authority);

        User user = new User();
        user.setFirstName(registerRequest.getFirstName());
        user.setLastName(registerRequest.getLastName());
        user.setUsername(registerRequest.getUsername());
        user.setEmail(registerRequest.getEmail());
        user.setStatus(AccountStatus.ACTIVE);
        user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
        user.setAuthorities(authorities);
        userService.save(user);

        registerRequestService.delete(registerRequest);

    }

    @PostMapping("/reject")
    public void reject(@RequestBody RegisterRequest registerRequest) {
        registerRequestService.delete(registerRequest);
    }
}
