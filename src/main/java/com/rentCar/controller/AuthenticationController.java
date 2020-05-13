package com.rentCar.controller;

import com.rentCar.dto.UserDTO;
import com.rentCar.dto.UserTokenState;
import com.rentCar.model.User;
import com.rentCar.security.TokenUtils;
import com.rentCar.security.auth.JwtAuthenticationRequest;
import com.rentCar.service.UserService;
import com.rentCar.service.impl.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/auth", produces = MediaType.APPLICATION_JSON_VALUE)
public class AuthenticationController {

    @Autowired
    TokenUtils tokenUtils;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private CustomUserDetailsService userDetailsService;

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtAuthenticationRequest authenticationRequest,
                                                       HttpServletResponse response) throws AuthenticationException, IOException {

        final Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(),
                        authenticationRequest.getPassword()));

        // Ubaci email + password u kontext
        SecurityContextHolder.getContext().setAuthentication(authentication);// ako su ispravni, korisnika treba da upisemo u security context holder
        //odnosno da nasa aplikacija bude svesna da je korisnik ulogovan
        // Kreiraj token
        User user = (User) authentication.getPrincipal();
        List<String> roles = new ArrayList<>();

        UserDTO userDTO = new UserDTO();
        userDTO.setFirstName(user.getFirstName());
        userDTO.setUsername(user.getUsername());
        userDTO.setEmail(user.getEmail());
        userDTO.setStatus(user.getStatus().name());

        for (GrantedAuthority authority : user.getAuthorities()) {
            roles.add(authority.getAuthority());
        }

        userDTO.setRoles(roles);

        String jwt = tokenUtils.generateToken(user.getUsername()); //kreiranje tokena je odredjeno u ovoj tokenUtils klasi, koju smo mi napisali
        int expiresIn = tokenUtils.getExpiredIn(); //koja ima funkcije za generisanje i validiranje jwt tokena


        return ResponseEntity.ok(new UserTokenState(jwt, expiresIn, userDTO)); //kroz ovaj dto objekat se salje token na klijent
    }

}
