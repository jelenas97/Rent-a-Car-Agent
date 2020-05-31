package com.rentCar.repository;

import com.rentCar.model.PriceList;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PriceListRepository extends JpaRepository<PriceList, Long> {

     List<PriceList> findByCreatorId(Long id);
}
