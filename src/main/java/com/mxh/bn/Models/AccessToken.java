package com.mxh.bn.Models;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity 
@Table (name = "access_token")
@Data 
public class AccessToken {
    
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY) 
    private int id;

    @Column (name = "id_user")
    private int idUser;

    @Column (name = "token")
    private String token;

    @Column (name = "hang_su_dung")
    private LocalDateTime hangSuDung;
}
