package com.rentCar.repository;

import com.rentCar.model.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MessageRepository extends JpaRepository<Message, Long> {
    @Query(value = "select m from Message m where m.rentRequest.id = ?1 order by m.date")
    List<Message> getRentRequestMessages(long id);
}
