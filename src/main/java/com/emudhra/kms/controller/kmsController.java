package com.emudhra.kms.controller;

import com.emudhra.kms.dto.KmsDataDto;
import com.emudhra.kms.model.KmsData;
import com.emudhra.kms.services.KmsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/")
public class kmsController {
    @Autowired
    private KmsService kmsService;

//    @GetMapping("/msg")
//    public String getMsg(){
//        return "hello";
//    }

    @GetMapping("/showAllData")
    public ResponseEntity<List<KmsData>> getAllKmsDataFromDB(){
        return kmsService.getAllKmsDataFromDB();
    }

    @PostMapping("/saveDataInDB")
    public ResponseEntity<String> saveDataInDB(KmsDataDto kmsDataDto){
        return kmsService.saveDataInDB(kmsDataDto);
    }

}
