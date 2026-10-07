package com.mxh.bn.Controllers;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mxh.bn.Dto.ReqDTO.UserLoginReq;
import com.mxh.bn.Dto.ResDTO.UserLoginRes;
import com.mxh.bn.Models.AccessToken;
import com.mxh.bn.Models.User;
import com.mxh.bn.Repo.AccessTokenRepo;
import com.mxh.bn.Services.AccessTokenService;
import com.mxh.bn.Services.UserService;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/auth")
public class LoginController {

    private final UserService userService;
    private final AccessTokenService accessTokenService;

    public LoginController(UserService userService, AccessTokenService accessTokenService) {
        this.userService = userService;
        this.accessTokenService = accessTokenService;
    }

    @PostMapping("/login")
    public ResponseEntity<UserLoginRes> login(@RequestBody UserLoginReq userLoginReq, HttpServletRequest req,
            HttpServletResponse res) {
        int idUserByUsernameAndPassword = userService.getIdByUsernameAndPassword(userLoginReq.getUsername(),
                userLoginReq.getPassword());

        UserLoginRes userLoginRes = new UserLoginRes();

        if (idUserByUsernameAndPassword != -1) {
            User user = userService.getUserById(idUserByUsernameAndPassword);
            userLoginRes.setId(user.getId());
            userLoginRes.setUsername(user.getUsername());
            userLoginRes.setFullName(user.getFullName());
            userLoginRes.setEmail(user.getEmail());
            userLoginRes.setGioiTinh(user.getGioiTinh());
            userLoginRes.setMessage("Đăng nhập thành công");
            HttpSession session = req.getSession(true);

            session.setAttribute("idUser", user.getId());


            String token = UUID.randomUUID().toString();

            AccessToken accessToken = new AccessToken();
            accessToken.setHangSuDung(LocalDateTime.now().plusDays(30));
            accessToken.setIdUser(user.getId());
            accessToken.setToken(token);

            accessTokenService.addAccessToken(accessToken);

            Cookie cookie = new Cookie("token", token);
            cookie.setMaxAge(30 * 24 * 60 * 60);
            cookie.setHttpOnly(true);
            cookie.setPath("/");
            res.addCookie(cookie);

            return ResponseEntity.status(HttpStatus.OK).body(userLoginRes);
        }

        userLoginRes.setMessage("Tên tài khoản hoặc mật khẩu không đúng");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(userLoginRes);
    }

}
