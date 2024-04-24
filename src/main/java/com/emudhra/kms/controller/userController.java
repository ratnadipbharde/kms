package com.emudhra.kms.controller;

import com.emudhra.kms.dto.UserLoginDto;
import com.emudhra.kms.model.Response;
import com.emudhra.kms.services.KmsService;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/user")
public class userController {
    KmsService kmsService;
    @PostMapping("/login")
    public Response userLogin(@RequestBody UserLoginDto userLoginDto){
        Response response=new Response();
        response.setObject("asdasda5sd6asf7asf6a7sfsaas");
        response.setString("admin");
        return response;
    }
}
