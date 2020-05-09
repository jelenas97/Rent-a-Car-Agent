package com.rentCar.service.impl;

import com.rentCar.model.Term;
import com.rentCar.repository.TermRepository;
import com.rentCar.service.TermService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TermServiceImpl implements TermService {

    @Autowired
    private TermRepository termRepository;

    @Override
    public void save(Term term) {
        this.termRepository.save(term);
    }
}
