package com.emudhra.kms.services;

import com.emudhra.kms.dto.UserLoginDto;
import com.emudhra.kms.dto.ResponseDto;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    ResponseEntity<ResponseDto> userLogin(UserLoginDto userLoginDto);
}
