package com.rentCar.repository;

import com.rentCar.model.CarBrand;
import com.rentCar.model.FuelType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FuelTypeRepository extends JpaRepository<FuelType, Long> {
}
