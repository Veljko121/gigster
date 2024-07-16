package com.github.veljko121.gigster.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.github.veljko121.gigster.model.RegisteredUser;

public interface RegisteredUserRepository extends JpaRepository<RegisteredUser, Integer> { }
