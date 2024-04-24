package com.emudhra.kms.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "app_user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String fName;
    private String lName;
    private String departmentName;
    private String role;
    private String email;
    private String userName;
    private String password;
    private Boolean isLogin;
}
