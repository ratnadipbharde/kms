package com.emudhra.kms.dto;

import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class UserLoginDto {
    private String userName;
    private String password;
}
