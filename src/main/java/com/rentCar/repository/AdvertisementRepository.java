package com.rentCar.repository;

import com.rentCar.dto.SearchDTO;
import com.rentCar.model.Advertisement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
@Repository
public interface AdvertisementRepository extends JpaRepository<Advertisement, Long> {

    @Query(value = "select a from Advertisement a where a.place = ?1 and a.startDate <= ?2 and a.endDate >= ?3")
    List<Advertisement> findAdvertisements(String place, LocalDate startDate, LocalDate endDate);
}
