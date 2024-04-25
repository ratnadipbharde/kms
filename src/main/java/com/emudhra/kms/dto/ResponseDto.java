package com.emudhra.kms.dto;

import lombok.Data;

@Data
public class ResponseDto {
    private Object token;
    private String roles;
    private String error;
}
