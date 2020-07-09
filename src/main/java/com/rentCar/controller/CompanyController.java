package com.rentCar.controller;

import com.rentCar.model.Authority;
import com.rentCar.model.Company;
import com.rentCar.service.AuthorityService;
import com.rentCar.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin("http://localhost:4200")
@RequestMapping("company")
public class CompanyController {

    @Autowired
    private CompanyService companyService;

    @Autowired
    private AuthorityService authorityService;

    @PostMapping
    public Company save(@RequestBody Company company) {
        Authority authority = authorityService.findByName("ROLE_COMPANY");
        List<Authority> authorities = new ArrayList<>();
        authorities.add(authority);
        company.setAuthorities(authorities);
        return companyService.save(company);
    }
}
