package com.rentCar.repository;

import com.rentCar.model.RentReport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RentReportRepository extends JpaRepository<RentReport, Long> {
    @Query(value = "select r from RentReport r where r.advertisement.id = ?1 ")
    List<RentReport> findAllCount(Long id);
}
