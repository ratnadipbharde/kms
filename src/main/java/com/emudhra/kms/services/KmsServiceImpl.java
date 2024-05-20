package com.emudhra.kms.services;

import com.emudhra.kms.dto.AddKmsDataDto;
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
import org.springframework.data.domain.Limit;
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
        List<KmsData> kmsData = kmsRepo.findAll();
        System.out.println(kmsData);
        System.out.println(genrateUniqueID());
        List<KmsDataDto> kmsDataDtoList = modelMapper.map(kmsData, new TypeToken<List<KmsDataDto>>() {
        }.getType());
        kmsDataDtoList.forEach(kmsDataDto -> Collections.reverse(kmsDataDto.getRemarks()));
        return ResponseEntity.ok(kmsDataDtoList);
    }

    @Override
    public ResponseEntity<String> saveKmsDataInDB(AddKmsDataDto addKmsDataDto) {
        try {
            KmsData kmsData = modelMapper.map(addKmsDataDto, KmsData.class);
            Remark remark = new Remark();
            remark.setRemark("Record Created");
            remark.setDate(String.valueOf(LocalDateTime.now()));
            kmsData.getRemarks().add(remark);
            System.out.println(kmsData);
            kmsData.setUniqueNumber(genrateUniqueID());
            kmsRepo.save(kmsData);
            return ResponseEntity.ok("save data successfully");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body("faild");
        }

    }

    @Override
    public ResponseEntity<String> addRemarkInProject(String uniqueNumber, RemarkDto remarkDto) {
        System.out.println("Remark : " + remarkDto);
        if (isUniqueNumberIsExist(uniqueNumber)) {
            try {
                KmsData kmsData = kmsRepo.findByUniqueNumber(uniqueNumber);
                kmsData.getRemarks().get(kmsData.getRemarks().size() - 1).setRemark(remarkDto.getRemark());
                kmsData.getRemarks().get(kmsData.getRemarks().size() - 1).setDate(String.valueOf(LocalDateTime.now()));
                System.out.println(kmsData);
                kmsRepo.save(kmsData);
                return ResponseEntity.ok("remark save");
            } catch (Exception e) {
                e.printStackTrace();
                return ResponseEntity.ok("faild");
            }
        } else {
            return ResponseEntity.ok("faild! project data missing");
        }

    }

    @Override
    public KmsData getKmsDataByUniqueID(String uniqueNumber) {
        KmsData kmsData = kmsRepo.findByUniqueNumber(uniqueNumber);
        Collections.reverse(kmsData.getRemarks());
        return kmsData;
    }

    public KmsData getKmsDatafromDB(String uniqueNumber) {
        return kmsRepo.findByUniqueNumber(uniqueNumber);
    }

    public boolean isUniqueNumberIsExist(String uniqueNumber) {
        try {
            return kmsRepo.existsByUniqueNumber(uniqueNumber);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public String genrateUniqueID() {
        List<KmsData> kmsData = kmsRepo.findAll();
        String uniqueNumber;
        int num = 10;
        for (int i = 1; i < num; i++) {
            uniqueNumber = "P" + (kmsData.size() + i);
            if (!isUniqueNumberIsExist(uniqueNumber)) {
                return uniqueNumber;
            } else {
                num++;
            }
        }
        return null;
    }
}
