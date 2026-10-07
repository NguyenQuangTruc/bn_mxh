package com.mxh.bn.Services;

import java.time.LocalDateTime;
import java.util.Optional;

import org.hibernate.event.spi.LockEvent;
import org.springframework.stereotype.Service;

import com.mxh.bn.Models.AccessToken;
import com.mxh.bn.Repo.AccessTokenRepo;

@Service 
public class AccessTokenService {

    private final AccessTokenRepo accessTokenRepo;

    public AccessTokenService(AccessTokenRepo accessTokenRepo) {
        this.accessTokenRepo = accessTokenRepo;
    }
    
    public AccessToken addAccessToken(AccessToken accessToken) {
        Optional<AccessToken> listAccessTokenByIdUser = accessTokenRepo.findByIdUser(accessToken.getIdUser());
        if (listAccessTokenByIdUser.isPresent()) {
            AccessToken newAccessToken = listAccessTokenByIdUser.get();
            newAccessToken.setToken(accessToken.getToken());
            return accessTokenRepo.save(newAccessToken);
        }
        return accessTokenRepo.save(accessToken);
    }

    public boolean checkToken(String token) {
        boolean isToken = accessTokenRepo.existsByToken(token);

        AccessToken accessToken = accessTokenRepo.findByToken(token).get();

        boolean isOutDate = accessToken.getHangSuDung().isBefore(LocalDateTime.now());

        if (isToken) {
            return false;
        }

        if (isOutDate) {
            accessTokenRepo.deleteByToken(token);
            return false;
        }
        return true;
    }

    public AccessToken getTokenByIdUser(int idUser) {
        AccessToken accessToken = accessTokenRepo.findByIdUser(idUser).get();

        boolean isOutDate = accessToken.getHangSuDung().isBefore(LocalDateTime.now());

        if (!isOutDate) {
            return accessToken;
        }
        return null;
    }
}
