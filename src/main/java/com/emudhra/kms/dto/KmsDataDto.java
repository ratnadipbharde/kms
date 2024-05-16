package com.emudhra.kms.dto;


import com.emudhra.kms.model.Remark;
import jakarta.persistence.Column;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Data
public class KmsDataDto {
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
    @Column(name = "PROJECT_ID")
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
    private List<RemarkDtoWithDate> remarks = new ArrayList<>();
}