package com.emudhra.kms.controller;

import com.emudhra.kms.services.KmsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class kmsController {
    @Autowired
    private KmsService kmsService;

    @GetMapping("/msg")
    public String getMsg(){
        return "hello";
    }
}
