package com.emudhra.kms.model;
import jakarta.persistence.*;
import lombok.Data;



@Entity
@Data
@Table(name = "REMARKS")
public class Remark {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String remark;
    private String date;
}

