package com.emudhra.kms.dto;

import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class AddRemarkDto {
    private String uniqueNumber;
    private RemarkDto remarkDto;
}
