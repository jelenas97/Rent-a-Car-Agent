package com.rentCar.repository;

import com.rentCar.dto.UserDTO;
import com.rentCar.model.Advertisement;
import com.rentCar.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserRepository  extends JpaRepository<User, Long> {
    @Query(value = "select u from User u where u.status <> 'DELETED'")
    List<User> findAllUsers();

    User findByEmail(String email);
}
