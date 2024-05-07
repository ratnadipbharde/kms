package com.emudhra.kms.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "KMS_DATA")
public class KmsData {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    //@Column(name = "SR_NO")
    private int sr_no;
    //@Column(name = "UNIQUEID")
    private String uniqueID;
    //@Column(name = "FINANCIAL_YEAR")
    private String financial_Year;
    //@Column(name = "PO_NO")
    private String po_No;
    //@Column(name = "PO_DATE")
    private String po_Date;
    //@Column(name = "SI_NAME")
    private String si_Name;
    //@Column(name = "PROJECT_NAME")
    private String project_Name;
   // @Column(name = "PROJECT_ID")
    private String project_ID;
    //@Column(name = "PRODUCT")
    private String product;
    //@Column(name = "VERTICAL")
    private String vertical;
    //@Column(name = "ACCOUNT_MANAGER")
    private String account_Manager;
    //@Column(name = "KICK_OFF")
    private String kick_Off;
    //@Column(name = "DEVELOPMENT")
    private String development;
    //@Column(name = "DEPLOYMENT")
    private String deployment;
    //@Column(name = "UAT")
    private String uat;
    //@Column(name = "GO_LIVE")
    private String go_Live;
    //@Column(name = "COMPLETED")
    private String completed;
    //@Column(name = "RAG")
    private String rag;
    //@Column(name = "PROJECT_MANAGER")
    private String project_Manager;
    //@Column(name = "LATEST_DATE")
    private String latest_Date;
    //@Column(name = "REMARK")
    private String remark;
    //@Column(name = "CI_CD_IMPLEMENTATION")
    private String cI_CD_Implementation;
    //@Column(name = "AGING")
    private String aging;
}
