package com.rentCar.repository;

import com.rentCar.model.CarBrand;
import com.rentCar.model.CarModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarModelRepository extends JpaRepository<CarModel, Long> {
}
