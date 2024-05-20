package com.emudhra.kms.dto;

import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data

public class RemarkDtoWithDate {
    private String remark;
    private String date;
}
