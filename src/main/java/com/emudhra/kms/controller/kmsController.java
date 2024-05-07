package com.emudhra.kms.controller;

import com.emudhra.kms.dto.KmsDataDto;
import com.emudhra.kms.model.KmsData;
import com.emudhra.kms.services.KmsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/")
public class kmsController {
    @Autowired
    private KmsService kmsService;

    @GetMapping("/showAllData")
    public ResponseEntity<List<KmsData>> getAllKmsDataFromDB(){
        return kmsService.getAllKmsDataFromDB();
    }

    @PostMapping("/saveDataInDB")
    public ResponseEntity<String> saveDataInDB(@RequestBody KmsDataDto kmsDataDto){
        return kmsService.saveDataInDB(kmsDataDto);
    }

    @PutMapping("updateKmsData")
    public ResponseEntity<String> updateKmsDataInDB(@RequestBody KmsDataDto kmsDataDto){
        return kmsService.updateKmsDataInDB(kmsDataDto);
    }
}
