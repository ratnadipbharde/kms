package com.emudhra.kms.services;

import com.emudhra.kms.dto.ResponseDto;
import com.emudhra.kms.dto.UserDto;
import com.emudhra.kms.dto.UserLoginDto;
import com.emudhra.kms.dto.LogInResponseDto;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    ResponseEntity<LogInResponseDto> userLogin(UserLoginDto userLoginDto);

    ResponseEntity<ResponseDto> getUserInfo(String token);
}
