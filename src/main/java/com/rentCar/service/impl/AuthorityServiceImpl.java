package com.rentCar.service.impl;

import com.rentCar.model.Authority;
import com.rentCar.repository.AuthorityRepository;
import com.rentCar.service.AuthorityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AuthorityServiceImpl implements AuthorityService {

    @Autowired
    private AuthorityRepository authorityRepository;

    @Override
    public List<Authority> findById(Long id) {
        Authority auth = this.authorityRepository.getOne(id);
        List<Authority> auths = new ArrayList<>();
        auths.add(auth);
        return auths;
    }

    @Override
    public Authority findByName(String name) {
        return this.authorityRepository.findByName(name);
    }
}
