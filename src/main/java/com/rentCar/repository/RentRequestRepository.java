package com.rentCar.repository;


import com.rentCar.enumerations.RentRequestStatus;
import com.rentCar.model.RentRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface RentRequestRepository extends JpaRepository<RentRequest, Long> {

    List<RentRequest> findBySenderIdAndRentRequestStatusAndEndDateTimeGreaterThanEqual(long id, RentRequestStatus status, LocalDateTime dateTime);

    List<RentRequest> findBySenderIdAndRentRequestStatusIn(long id, List<RentRequestStatus> statuses);

    @Query(value = "select a from RentRequest a where a.id = ?1")
    RentRequest find(Long id);

    @Query(value = "select t from RentRequest t where t.advertisement.id = ?1 and t.rentRequestStatus='PENDING' and t.startDate <= ?2 and t.endDate >= ?2 " +
            "or t.advertisement.id = ?1 and t.canceled=false and t.startDate <= ?3 and t.endDate >= ?3 " +
            "or t.advertisement.id = ?1 and t.canceled=false and t.startDate >= ?2 and t.endDate <= ?3")
    List<RentRequest> findPending(Long id, LocalDate startDate, LocalDate endDate);
}
