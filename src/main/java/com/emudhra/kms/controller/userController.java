package com.emudhra.kms.controller;

import com.emudhra.kms.dto.*;
import com.emudhra.kms.dto.LogInResponseDto;
import com.emudhra.kms.dto.ResetPasswordDto;
import com.emudhra.kms.dto.UserDto;
import com.emudhra.kms.dto.UserLoginDto;
import com.emudhra.kms.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@CrossOrigin
@RestController
@RequestMapping("/")
public class userController {
    @Autowired
    UserService userService;

    @PostMapping("/login")
    public ResponseEntity<LogInResponseDto> userLogin(@RequestBody UserLoginDto userLoginDto){
        return userService.userLogin(userLoginDto);
    }

    @PostMapping("/resetPassword")
    public ResponseEntity<ResponseDto>resetPassword(@RequestBody ResetPasswordDto resetPasswordDto, @RequestHeader String token){
        return userService.resetPassword(resetPasswordDto,token);
    }

    @PostMapping("/logout")
    public ResponseEntity<ResponseDto> userLogout(@RequestHeader String token){
        return userService.userLogout(token);
    }

    @PostMapping("/getUserInfo")
    public ResponseEntity<ResponseDto> getUserInfo(@RequestHeader String token){
        return userService.getUserInfo(token);
    }

    @PostMapping("/addUser")
    public  ResponseEntity<ResponseDto>addUserInDatabase(@RequestBody UserDto userDto, @RequestHeader String token){
        return userService.addUserInDatabase(userDto, token);
    }
}
