package com.mxh.bn.Models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity 
@Table (name = "users")
@Data 
public class User {
    
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY) 
    private int id;

    @Column (name = "username", length = 100, nullable = false)
    private String username;

    @Column (name = "password", length = 100, nullable = false)
    private String password;

    @Column (name = "full_name", length = 200, nullable = false)
    private String fullName;

    @Column (name = "email", nullable = false)
    private String email;

    @Column (name = "gioi_tinh", nullable = false)
    private String gioiTinh;
}
