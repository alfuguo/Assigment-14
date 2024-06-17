package com.coderscampus.al_assigment_14.repository;

import com.coderscampus.al_assigment_14.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface userRepository extends JpaRepository<User, Long> {
public List<User> findByUsername(String username);
}

