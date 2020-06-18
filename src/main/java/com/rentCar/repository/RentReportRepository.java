package com.rentCar.repository;

import com.rentCar.model.RentReport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RentReportRepository extends JpaRepository<RentReport, Long> {
}
