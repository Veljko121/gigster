package com.github.veljko121.gigster.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.github.veljko121.gigster.model.User;

public interface UserRepository extends JpaRepository<User, Integer> {
    
    Optional<User> findByUsername(String username);

    Optional<User> findByEmail(String email);

    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);

}
