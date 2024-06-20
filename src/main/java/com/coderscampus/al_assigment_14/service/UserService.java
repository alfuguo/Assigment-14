package com.coderscampus.al_assigment_14.service;

import com.coderscampus.al_assigment_14.domain.User;
import com.coderscampus.al_assigment_14.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User createUser(User user) {
        return userRepository.save(user);
    }

    public User getUserById(Long id) {
        return userRepository.findById(id);
    }

}
