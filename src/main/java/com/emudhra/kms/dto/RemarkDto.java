package com.emudhra.kms.dto;

import com.emudhra.kms.model.KmsData;
import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class RemarkDto {
    private String remark;
    private String date;
}
