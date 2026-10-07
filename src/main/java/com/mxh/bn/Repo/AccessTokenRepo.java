package com.mxh.bn.Repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mxh.bn.Models.AccessToken;

public interface AccessTokenRepo extends JpaRepository<AccessToken, Integer> {

    boolean existsByToken(String token);

    Optional<AccessToken> findByToken(String token);

    void deleteByToken(String token);

    Optional<AccessToken> findByIdUser(int idUser);
    
}
