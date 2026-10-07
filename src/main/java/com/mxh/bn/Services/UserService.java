package com.mxh.bn.Services;

import java.util.List;
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

    public int getIdByUsernameAndPassword(String username, String password) {
        try {
            Optional<User> listUser = userRepo.findByUsername(username);
            if (listUser.isPresent()) {
                boolean isPassword = passwordEncoder.matches(password, listUser.get().getPassword());
                if (isPassword) {
                    return listUser.get().getId();
                }
            }
            return -1;

        } catch (Exception e) {
            // TODO: handle exception
            return -1;
        }
    }

    public User getUserById(int id) {
        try {
            User user = userRepo.findById(id).get();
            return user;
        } catch (Exception e) {
            // TODO: handle exception
            return null;
        }
    }

}
