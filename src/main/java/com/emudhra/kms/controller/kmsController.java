package com.emudhra.kms.controller;

import com.emudhra.kms.dto.AddKmsDataDto;
import com.emudhra.kms.dto.AddRemarkDto;
import com.emudhra.kms.dto.KmsDataDto;
import com.emudhra.kms.dto.RemarkDto;
import com.emudhra.kms.model.KmsData;
import com.emudhra.kms.model.Remark;
import com.emudhra.kms.repository.KmsRepo;
import com.emudhra.kms.repository.RemarkRepo;
import com.emudhra.kms.services.KmsService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/")
public class kmsController {
    @Autowired
    private KmsService kmsService;

    @Autowired
    KmsRepo kmsRepo;
    @Autowired
    RemarkRepo remarkRepo;
    @Autowired
    ModelMapper modelMapper;


    @GetMapping("/showAllData")
    public ResponseEntity<List<KmsDataDto>> getAllKmsDataFromDB() {
        return kmsService.getAllKmsDataFromDB();
    }

    @PostMapping("/addProject")
    public ResponseEntity<String> addKmsProjectDataInDB(@RequestBody AddKmsDataDto addKmsDataDto){
        System.out.println(addKmsDataDto);
        return kmsService.saveKmsDataInDB(addKmsDataDto);
    }

    @PostMapping("/addRemark")
    public ResponseEntity<String>addRemarkInProject(@RequestParam String uniqueNumber,@RequestBody RemarkDto remarkDto){
        return kmsService.addRemarkInProject(uniqueNumber,remarkDto);
    }

    @PostMapping("/getDataByUniqueID")
    public KmsData getKmsDataByUniqueID(@RequestParam String uniqueNumber){
        return kmsService.getKmsDataByUniqueID(uniqueNumber);
    }


}

