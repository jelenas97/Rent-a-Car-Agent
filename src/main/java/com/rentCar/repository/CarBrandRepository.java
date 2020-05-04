package com.rentCar.repository;

import com.rentCar.model.CarBrand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface CarBrandRepository extends JpaRepository<CarBrand, Long> {
    CarBrand findByName(String name);
    @Query(value = "select c from CarBrand c where c.active=true")
    List<CarBrand> getActiveCarBrands();
}
