package com.rentCar.repository;


import com.rentCar.enumerations.RentRequestStatus;
import com.rentCar.model.RentRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface RentRequestRepository extends JpaRepository<RentRequest, Long> {

    List<RentRequest> findBySenderIdAndRentRequestStatusAndEndDateTimeGreaterThanEqual(long id, RentRequestStatus status, LocalDateTime dateTime);

    List<RentRequest> findBySenderIdAndRentRequestStatusIn(long id, List<RentRequestStatus> statuses);

}
