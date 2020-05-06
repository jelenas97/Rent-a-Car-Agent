package com.rentCar.service;

import com.rentCar.dto.UserDTO;
import com.rentCar.model.User;

import java.util.List;

public interface UserService {
    List<UserDTO> findAllUsers();
    void changeStatus(UserDTO user);


}
