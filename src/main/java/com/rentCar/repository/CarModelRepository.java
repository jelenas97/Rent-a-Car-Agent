package com.rentCar.repository;
import com.rentCar.model.CarModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CarModelRepository extends JpaRepository<CarModel, Long> {
    CarModel findByName(String name);
    @Query(value = "select c from CarModel c where c.active=true and c.carBrand.id=?1")
    List<CarModel> getActiveCarModels(Long brand);
}
