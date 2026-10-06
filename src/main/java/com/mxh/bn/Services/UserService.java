package com.mxh.bn.Services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.mxh.bn.Models.User;
import com.mxh.bn.Repo.UserRepo;

@Service
public class UserService {

    private final UserRepo userRepo;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepo userRepo, PasswordEncoder passwordEncoder) {
        this.userRepo = userRepo;
        this.passwordEncoder = passwordEncoder;
    }

    // ham them user vao database
    public boolean addUser(User user) {

        try {
            String passwordEndcoder = passwordEncoder.encode(user.getPassword());
            user.setPassword(passwordEndcoder);
            userRepo.save(user);
            return true;
        } catch (Exception e) {
            // TODO: handle exception
            return false;
        }

    }

}
