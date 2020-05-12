package com.rentCar.repository;

import com.rentCar.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @Query(value = "select u from User u where u.status <> 'DELETED'")
    List<User> findAllUsers();

    User findByEmail(String email);

    User findByUsername(String username);

    @Query(value = "select u from User u where u.id = ?1")
    User find(Long id);
}
