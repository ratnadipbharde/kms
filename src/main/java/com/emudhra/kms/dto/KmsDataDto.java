package com.emudhra.kms.dto;


import com.emudhra.kms.model.Remark;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Data
public class KmsDataDto {
    private String uniqueNumber;
    private List<RemarkDto> remarks = new ArrayList<>();
}