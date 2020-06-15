package com.rentCar.service.impl;

import com.rentCar.model.Advertisement;
import com.rentCar.model.Term;
import com.rentCar.repository.TermRepository;
import com.rentCar.service.AdvertisementService;
import com.rentCar.service.TermService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class TermServiceImpl implements TermService {

    @Autowired
    private TermRepository termRepository;

    @Autowired
    private AdvertisementService advertisementService;

    @Override
    public void save(Long id, LocalDateTime startDate, LocalDateTime endDate) {
        LocalDate start = LocalDate.of(startDate.getYear(), startDate.getMonth(), startDate.getDayOfMonth());
        LocalDate end = LocalDate.of(endDate.getYear(), endDate.getMonth(), endDate.getDayOfMonth());
        Advertisement a = this.advertisementService.find(id);
        this.termRepository.save(new Term(start, end, a));
    }

    @Override
    public List<Term> findTakenTerm(Long id, LocalDateTime startDate, LocalDateTime endDate) {
        LocalDate start = LocalDate.of(startDate.getYear(), startDate.getMonth(), startDate.getDayOfMonth());
        LocalDate end = LocalDate.of(endDate.getYear(), endDate.getMonth(), endDate.getDayOfMonth());
        System.out.println(id);
        return this.termRepository.findTakenTerm(id, start, end);

    }

    @Override
    public List<Term> getAllRentedFromCurrentAgent(Long id) {
        return termRepository.getAllRentedByAgentId(id);
    }


}
