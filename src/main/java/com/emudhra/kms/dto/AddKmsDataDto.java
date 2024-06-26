package com.emudhra.kms.dto;

import jakarta.persistence.Column;
import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class AddKmsDataDto {
    private String financial_Year;
    private String po_No;
    private String po_Date;
    private String si_Name;
    private String project_Name;
    private String projectNumber;
    private String product;
    private String vertical;
    private String account_Manager;
    private String kick_Off;
    private String development;
    private String deployment;
    private String uat;
    private String go_Live;
    private String completed;
    private String rag;
    private String project_Manager;
    private String latest_Date;
    private String cI_CD_Implementation;
    private String aging;
}
