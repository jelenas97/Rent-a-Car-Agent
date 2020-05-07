package com.rentCar.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RequestsHolderRepository extends JpaRepository<com.rentCar.model.RequestsHolder, Long> {
}
