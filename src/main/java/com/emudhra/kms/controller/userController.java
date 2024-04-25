package com.emudhra.kms.controller;

import com.emudhra.kms.dto.UserLoginDto;
import com.emudhra.kms.dto.ResponseDto;
import com.emudhra.kms.services.UserService;
import com.emudhra.kms.utility.JwtUitility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/")
public class userController {
    @Autowired
    UserService userService;

    @PostMapping("/login")
    public ResponseEntity<ResponseDto> userLogin(@RequestBody UserLoginDto userLoginDto){
        return userService.userLogin(userLoginDto);
    }
}
