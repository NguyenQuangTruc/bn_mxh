package com.mxh.bn.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.mxh.bn.Dto.ReqDTO.UserRegisterReq;
import com.mxh.bn.Models.User;
import com.mxh.bn.Services.RegisterService;

@RestController 
public class RegisterController {

    private final RegisterService registerService;
    @Autowired 
    public RegisterController(RegisterService registerService) {
        this.registerService = registerService;
    }
    
    @PostMapping ("/user")
    public ResponseEntity<?> createUser(@RequestBody UserRegisterReq userRegisterReq) {

        String password = userRegisterReq.getPassword();
        if (password.length() < 7) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Mật khẩu phải có ít nhất 7 ký tự!");
        }

        String email = userRegisterReq.getEmail();
        if (!email.contains("@")) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Sai mail");
        }

        User user = new User();
        user.setUsername(userRegisterReq.getUsername());
        user.setPassword(userRegisterReq.getPassword());
        user.setFullName(userRegisterReq.getFullName());
        user.setEmail(userRegisterReq.getEmail());
        user.setGioiTinh(userRegisterReq.getGioiTinh());

        return registerService.register(user);
    }
}
