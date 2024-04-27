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
    private String firstName;
    private String lastName;

    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department;

    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;

    private String email;
    private String userName;
    private String password;
    private Boolean isLogin;
}

