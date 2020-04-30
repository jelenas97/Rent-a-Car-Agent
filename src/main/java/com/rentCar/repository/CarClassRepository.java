package com.rentCar.repository;

import com.rentCar.model.CarClass;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CarClassRepository extends JpaRepository<CarClass, Long> {
    CarClass findByName(String name);
    @Query(value = "select c from CarClass c where c.active=true")
    List<CarClass> getActiveCarClasses();
}
