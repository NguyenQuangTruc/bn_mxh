package com.mxh.bn.Dto.ReqDTO;

import lombok.Data;

@Data 
public class UserRegisterReq {
    private String username;
    private String password;
    private String fullName;
    private String email;
    private String gioiTinh;
}
