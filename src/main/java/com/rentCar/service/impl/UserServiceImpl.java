package com.rentCar.service.impl;

import com.rentCar.dto.UserDTO;
import com.rentCar.enumerations.AccountStatus;
import com.rentCar.model.User;
import com.rentCar.repository.UserRepository;
import com.rentCar.service.AuthorityService;
import com.rentCar.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthorityService authorityService;

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<UserDTO> findAllUsers() {
        this.userRepository.findAllUsers();
        List<UserDTO> users = new ArrayList<>();
        for (User u : this.userRepository.findAllUsers()) {
            users.add(new UserDTO(u));
        }
        return users;
    }

    @Override
    public void changeStatus(UserDTO userDTO) {
        User user = this.userRepository.findByEmail(userDTO.getEmail());
        user.setStatus(AccountStatus.valueOf(userDTO.getStatus()));
        this.userRepository.save(user);

    }

    @Override
    public User findOne(String email) {
        return this.userRepository.findByEmail(email);
    }

    @Override
    public User findByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username);
    }

    @Override
    public User save(User user) {
        userRepository.save(user);
        return user;
    }

    @Override
    public User findById(Long id) {
        return this.userRepository.findOneById(id);
    }


}
