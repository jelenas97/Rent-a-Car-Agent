package com.rentCar.service.impl;

import com.rentCar.model.Company;
import com.rentCar.repository.CompanyRepository;
import com.rentCar.service.CompanyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CompanyServiceImpl implements CompanyService {

    private final CompanyRepository companyRepository;

    @Override
    public Company save(Company company) {
        return companyRepository.save(company);
    }
}
