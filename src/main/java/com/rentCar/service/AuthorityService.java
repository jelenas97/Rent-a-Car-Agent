package com.rentCar.service;

import com.rentCar.model.Authority;

import java.util.List;

public interface AuthorityService {
    List<Authority> findById(Long id);

    Authority findByName(String name);
}
