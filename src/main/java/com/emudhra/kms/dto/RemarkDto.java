package com.emudhra.kms.dto;

import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class RemarksDto {
    private String sr_no;
    private String remark;
    private String date;
}
