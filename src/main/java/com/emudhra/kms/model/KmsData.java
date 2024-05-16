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
    @Column(name = "FINANCIAL_YEAR")
    private String financial_Year;
    @Column(name = "PO_NO")
    private String po_No;
    @Column(name = "PO_DATE")
    private String po_Date;
    @Column(name = "SI_NAME")
    private String si_Name;
    @Column(name = "PROJECT_NAME")
    private String project_Name;
    @Column(name = "PROJECT_NUMBER")
    private String projectNumber;
    @Column(name = "PRODUCT")
    private String product;
    @Column(name = "VERTICAL")
    private String vertical;
    @Column(name = "ACCOUNT_MANAGER")
    private String account_Manager;
    @Column(name = "KICK_OFF")
    private String kick_Off;
    @Column(name = "DEVELOPMENT")
    private String development;
    @Column(name = "DEPLOYMENT")
    private String deployment;
    @Column(name = "UAT")
    private String uat;
    @Column(name = "GO_LIVE")
    private String go_Live;
    @Column(name = "COMPLETED")
    private String completed;
    @Column(name = "RAG")
    private String rag;
    @Column(name = "PROJECT_MANAGER")
    private String project_Manager;
    @Column(name = "LATEST_DATE")
    private String latest_Date;
    @Column(name = "CI_CD_IMPLEMENTATION")
    private String cI_CD_Implementation;
    @Column(name = "AGING")
    private String aging;

    @OneToMany(targetEntity = Remark.class, cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinColumn(name = "kms_data_id_fk", referencedColumnName = "id")
    private List<Remark> remarks = new ArrayList<>();


}
