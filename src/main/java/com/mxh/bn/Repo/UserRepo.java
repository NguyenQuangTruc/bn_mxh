package com.mxh.bn.Repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mxh.bn.Models.User;

public interface UserRepo extends JpaRepository<User, Integer> {

    Optional<User> findByUsername(String username);

    
}