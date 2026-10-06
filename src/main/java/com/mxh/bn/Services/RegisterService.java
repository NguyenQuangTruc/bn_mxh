package com.mxh.bn.Services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.mxh.bn.Dto.ResDTO.UserRes;
import com.mxh.bn.Models.User;
import com.mxh.bn.Repo.UserRepo;

@Service
public class RegisterService {

    private final UserRepo userRepo;
    private final UserService userService;

    @Autowired 
    public RegisterService(UserRepo userRepo, UserService userService) {
        this.userRepo = userRepo;
        this.userService = userService;
    }

    public ResponseEntity<?> register(User user) {
        try {
            Optional<User> listUserByUsername = userRepo.findByUsername(user.getUsername());
            if (!listUserByUsername.isEmpty()) {
                return ResponseEntity.status(HttpStatus.CONFLICT).body("Trùng user rồi cu!");
            }
            boolean isAddUser = userService.addUser(user);
            if (isAddUser) {
                UserRes userRes = new UserRes();
                userRes.setId(user.getId());
                userRes.setUsername(user.getUsername());
                userRes.setFullName(user.getFullName());
                userRes.setEmail(user.getEmail());
                userRes.setGioiTinh(user.getGioiTinh());
            
                return ResponseEntity.status(HttpStatus.CREATED).body(userRes);
            }

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("lỗi hệ thống");
        } catch (Exception e) {
            // TODO: handle exception
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("lỗi hệ thống");
        }
    }

    
}
