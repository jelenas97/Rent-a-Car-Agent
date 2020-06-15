package com.rentCar.service;

import com.rentCar.model.Term;

import java.time.LocalDateTime;
import java.util.List;

public interface TermService {
    void save(Long id, LocalDateTime startDate, LocalDateTime endDate);

    List<Term> findTakenTerm(Long id, LocalDateTime startDate, LocalDateTime endDate);

    List<Term> getAllRentedFromCurrentAgent(Long id);

}
