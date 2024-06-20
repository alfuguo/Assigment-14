package com.coderscampus.al_assigment_14.service;

import com.coderscampus.al_assigment_14.domain.User;
import com.coderscampus.al_assigment_14.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;
    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User saveUser(User user) {
        return userRepository.save(user);
    }
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }
    public User findById(Long userId) {
        return userRepository.findById(userId);
    }
    public List<User> findAll() {
        return userRepository.findAll();
    }



}
