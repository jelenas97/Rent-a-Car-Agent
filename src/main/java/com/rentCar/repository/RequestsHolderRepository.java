package com.rentCar.repository;

import com.rentCar.model.RequestsHolder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RequestsHolderRepository extends JpaRepository<com.rentCar.model.RequestsHolder, Long> {
    @Query(value = "select c from RequestsHolder c inner join c.rentRequests req")
    List<RequestsHolder> getAllPending(Long id);
}
