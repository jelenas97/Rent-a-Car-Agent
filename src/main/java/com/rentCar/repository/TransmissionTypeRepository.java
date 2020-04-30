package com.rentCar.repository;

import com.rentCar.model.CarBrand;
import com.rentCar.model.TransmissionType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TransmissionTypeRepository extends JpaRepository<TransmissionType, Long> {
    TransmissionType findByName(String name);
    @Query(value = "select c from TransmissionType c where c.active=true")
    List<TransmissionType> getActiveTransTypes();
}
