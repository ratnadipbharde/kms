package com.emudhra.kms.model;

import jakarta.persistence.*;
import lombok.Data;


@Entity
@Data
@Table(name = "department")
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String departmentName;
}