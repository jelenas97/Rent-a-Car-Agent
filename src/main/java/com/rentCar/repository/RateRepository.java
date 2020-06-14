package com.rentCar.repository;

import com.rentCar.model.Rate;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;
import java.util.List;

public interface RateRepository extends JpaRepository<Rate, Long> {

    List<Rate> findByClientId(long id);
    List<Rate> findByAdvertisementCarId(long id);
}
