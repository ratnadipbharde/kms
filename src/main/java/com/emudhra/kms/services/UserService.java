package com.emudhra.kms.services;

import com.emudhra.kms.dto.*;
import com.emudhra.kms.dto.LogInResponseDto;
import com.emudhra.kms.dto.ResetPasswordDto;
import com.emudhra.kms.dto.UserDto;
import com.emudhra.kms.dto.UserLoginDto;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    ResponseEntity<LogInResponseDto> userLogin(UserLoginDto userLoginDto);

    ResponseEntity<ResponseDto> getUserInfo(String token);

    ResponseEntity<ResponseDto> userLogout(String token);

    ResponseEntity<ResponseDto> addUserInDatabase(UserDto userDto, String token);

    ResponseEntity<ResponseDto> resetPassword(ResetPasswordDto resetPasswordDto, String token);
}
