package com.rentCar.repository;

import com.rentCar.model.CarBrand;
import com.rentCar.model.TransmissionType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransmissionTypeRepository extends JpaRepository<TransmissionType, Long> {
}
