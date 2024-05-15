package com.emudhra.kms.services;

import com.emudhra.kms.dto.AddRemarkDto;
import com.emudhra.kms.dto.KmsDataDto;
import com.emudhra.kms.dto.RemarkDto;
import com.emudhra.kms.model.KmsData;
import com.emudhra.kms.model.Remark;
import com.emudhra.kms.repository.KmsRepo;
import com.emudhra.kms.repository.RemarkRepo;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

@Service
public class KmsServiceImpl implements KmsService {

    @Autowired
    KmsRepo kmsRepo;
    @Autowired
    RemarkRepo remarkRepo;
    @Autowired
    ModelMapper modelMapper;



    @Override
    public ResponseEntity<List<KmsDataDto>> getAllKmsDataFromDB() {
        List<KmsData>kmsData=kmsRepo.findAll();
        System.out.println(kmsData);
        List<KmsDataDto> kmsDataDtoList = modelMapper.map(kmsData, new TypeToken<List<KmsDataDto>>() {}.getType());
        return ResponseEntity.ok(kmsDataDtoList);
    }

    @Override
    public ResponseEntity<String> saveKmsDataInDB(KmsDataDto kmsDataDto) {
        try {
            KmsData kmsData=modelMapper.map(kmsDataDto,KmsData.class);
            kmsData.getRemarks().get(0).setDate(String.valueOf(LocalDateTime.now()));
            System.out.println(kmsData);
            kmsRepo.save(kmsData);
            return ResponseEntity.ok("save data successfully");
        }
     catch (Exception e){
         return ResponseEntity.ok("faild");
     }
    }

    @Override
    public ResponseEntity<String> addRemarkInProject(AddRemarkDto addRemarkDto) {
        System.out.println(addRemarkDto);
        try {
            KmsData kmsData=kmsRepo.findByUniqueNumber(addRemarkDto.getUniqueNumber());
            kmsData.getRemarks().add(modelMapper.map(addRemarkDto.getRemarkDto(),Remark.class));
            kmsData.getRemarks().get(kmsData.getRemarks().size()-1).setDate(String.valueOf(LocalDateTime.now()));
            kmsRepo.save(kmsData);
            return ResponseEntity.ok("remark save");
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.ok("faild");
        }

    }
    public KmsData getKmsDatafromDB(String uniqueNumber){
        return kmsRepo.findByUniqueNumber(uniqueNumber);
    }

}












