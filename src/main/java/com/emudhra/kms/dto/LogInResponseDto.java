package com.emudhra.kms.dto;

import lombok.Data;

@Data
public class LogInResponseDto {
    private Object token;
    private String roles;
    private Boolean isFirstLogin;
    private String error;
}
