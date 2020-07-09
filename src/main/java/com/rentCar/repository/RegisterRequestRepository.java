package com.rentCar.repository;

import com.rentCar.model.RegisterRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RegisterRequestRepository extends JpaRepository<RegisterRequest, Long> {
}
