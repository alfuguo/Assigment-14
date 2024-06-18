package com.coderscampus.al_assigment_14.repository;

import com.coderscampus.al_assigment_14.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class UserRepository  {
    private Map<Long, User> userMap = new HashMap<>();

    public User save(User user) {
        if (user.getId() == null) {
            user.setId(generateId());
        }
        userMap.put(user.getId(), user);
        return user;
    }

    private long idGenerator = 1;
    private synchronized long generateId() {
        return idGenerator++;
    }

    public User findById(long id) {
        return userMap.get(id);
    }

    public User findByUsername(String username) {
        return userMap.values().stream()
                .filter(user -> user.getUsername().equals(username))
                .findFirst()
                .orElse(null);
    }
}


