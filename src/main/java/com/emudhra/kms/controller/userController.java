package com.emudhra.kms.controller;

import com.emudhra.kms.dto.ResponseDto;
import com.emudhra.kms.dto.UserDto;
import com.emudhra.kms.dto.UserLoginDto;
import com.emudhra.kms.dto.LogInResponseDto;
import com.emudhra.kms.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/")
public class userController {
    @Autowired
    UserService userService;

    @PostMapping("/login")
    public ResponseEntity<LogInResponseDto> userLogin(@RequestBody UserLoginDto userLoginDto){
        return userService.userLogin(userLoginDto);
    }

    @PostMapping("/getUserInfo")
    public ResponseEntity<ResponseDto> getUserInfo(@RequestHeader String token){
        return userService.getUserInfo(token);
    }
}
