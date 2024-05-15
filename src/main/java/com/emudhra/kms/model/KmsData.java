package com.emudhra.kms.model;

import com.emudhra.kms.dto.RemarkDto;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.util.ArrayList;
import java.util.List;


@Entity
@Data
@Table(name = "KMS_DATA")
public class KmsData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String uniqueNumber;


    @OneToMany(targetEntity = Remark.class, cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinColumn(name = "kms_data_id_fk", referencedColumnName = "id")
    private List<Remark> remarks = new ArrayList<>();


}
