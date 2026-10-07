package com.mxh.bn.Dto.ResDTO;

import lombok.Data;

@Data 
public class UserLoginRes {
    private int id;
    private String username;
    private String fullName;
    private String email;
    private String gioiTinh;
    private String message;
}
